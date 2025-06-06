package ovh.mythmc.union.bukkit.economy.v1;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.BankAccount;
import ovh.mythmc.union.economy.v1.account.UniqueAccount;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.provider.EconomyProvider;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;

public final class VaultEconomyBridge implements Economy, EconomyBridge {

    private final EconomyProvider economyProvider;

    private final JavaPlugin plugin;

    private VaultEconomyBridge(@NotNull EconomyProvider economyProvider, @NotNull JavaPlugin plugin) {
        this.economyProvider = economyProvider;
        this.plugin = plugin;
    }

    private static OfflinePlayer offlinePlayer(@NotNull String name) {
        return Arrays.stream(Bukkit.getOfflinePlayers())
            .filter(offlinePlayer -> offlinePlayer.getName().equalsIgnoreCase(name))
            .findAny().orElse(null);
    }

    @Override
    public void register() {
        Bukkit.getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, this, this.plugin, ServicePriority.Highest);    
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final Optional<BankAccount> optionalAccount = this.economyProvider.findBankAccount(name);
        if (optionalAccount.isEmpty()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Unknown error while finding account " + name);
        }

        return new EconomyResponse(optionalAccount.get().balance().doubleValue(), optionalAccount.get().balance().doubleValue(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final Optional<BankAccount> optionalAccount = this.economyProvider.findBankAccount(name);
        if (optionalAccount.isEmpty()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Unknown error while finding account " + name);
        }

        final TransactionResult result = optionalAccount.get().deposit(BigDecimal.valueOf(amount));
        if (!result.isSuccess()) {
            return new EconomyResponse(amount, optionalAccount.get().balance().doubleValue(), null, result.resultType().name());
        }

        return new EconomyResponse(result.transaction().amount().doubleValue(), result.transaction().account().balance().doubleValue(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final Optional<BankAccount> optionalAccount = this.economyProvider.findBankAccount(name);
        if (optionalAccount.isEmpty()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Unknown error while finding account " + name);
        }

        if (!optionalAccount.get().has(BigDecimal.valueOf(amount)))
            return new EconomyResponse(amount, optionalAccount.get().balance().doubleValue(), ResponseType.FAILURE, "Not enough funds");

        return new EconomyResponse(amount, optionalAccount.get().balance().doubleValue(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final Optional<BankAccount> optionalAccount = this.economyProvider.findBankAccount(name);
        if (optionalAccount.isEmpty()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Unknown error while finding account " + name);
        }

        final TransactionResult result = optionalAccount.get().withdraw(BigDecimal.valueOf(amount));
        if (!result.isSuccess()) {
            return new EconomyResponse(amount, optionalAccount.get().balance().doubleValue(), null, result.resultType().name());
        }

        return new EconomyResponse(result.transaction().amount().doubleValue(), result.transaction().account().balance().doubleValue(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse createBank(String name, String ownerName) {
        return createBank(name, offlinePlayer(ownerName));
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer owner) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " already exists");
        }

        final var result = this.economyProvider.createBankAccount(name);
        return new EconomyResponse(0, result.account().get().balance().doubleValue(), ResponseType.SUCCESS, null);
    }

    @Override
    public boolean createPlayerAccount(String name) {
        return createPlayerAccount(offlinePlayer(name));
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        // Check if user account already exists
        if (this.economyProvider.uniqueAccountExists(player.getUniqueId())) {
            return false;
        }

        return this.economyProvider.createUniqueAccount(player.getUniqueId()).isSuccess();
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(offlinePlayer(playerName), worldName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        if (this.economyProvider.features().perWorldEconomy()) {
            final var options = AccountOptions.builder()
                .worldName(worldName)
                .build();

            return this.economyProvider.createUniqueAccount(player.getUniqueId(), options).isSuccess();
        }

        return createPlayerAccount(player);
    }

    @Override
    public String currencyNamePlural() {
        return LegacyComponentSerializer.legacySection().serialize(this.economyProvider.defaultCurrency().displayNamePlural());
    }

    @Override
    public String currencyNameSingular() {
        return LegacyComponentSerializer.legacySection().serialize(this.economyProvider.defaultCurrency().displayNameSingular());
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final var result = this.economyProvider.deleteBankAccount(name);
        if (!result.isSuccess()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Unknown error while deleting bank account " + name);
        }

        return new EconomyResponse(0, 0, ResponseType.SUCCESS, name);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return depositPlayer(offlinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        // Check if user account exists
        if (!this.economyProvider.uniqueAccountExists(player.getUniqueId())) {
            return new EconomyResponse(amount, 0, ResponseType.FAILURE, "User account " + player.getUniqueId() + " doesn't exist");
        }

        final Optional<UniqueAccount> optionalAccount = this.economyProvider.findUniqueAccount(player.getUniqueId());
        final TransactionResult result = optionalAccount.get().deposit(BigDecimal.valueOf(amount));
        if (!result.isSuccess()) {
            return new EconomyResponse(result.transaction().amount().doubleValue(), result.transaction().account().balance().doubleValue(), ResponseType.FAILURE, result.resultType().name());
        }

        return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(offlinePlayer(playerName), worldName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        // Todo: world support?
        return depositPlayer(player, amount);
    }

    @Override
    public String format(double amount) {
        final Component component = this.economyProvider.defaultCurrency().format(BigDecimal.valueOf(amount));
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

    @Override
    public int fractionalDigits() {
        return this.economyProvider.defaultCurrency().fractionalDigits();
    }

    @Override
    public double getBalance(String playerName) {
        return getBalance(offlinePlayer(playerName));
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        final Account<?> account = this.economyProvider.findUniqueAccount(player.getUniqueId()).get();
        return account.balance().doubleValue();
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        // Todo: world support?
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String worldName) {
        // Todo: world support?
        return getBalance(player);
    }

    @Override
    public List<String> getBanks() {
        return this.economyProvider.bankAccounts().stream()
            .map(BankAccount::identifier)
            .toList();
    }

    @Override
    public String getName() {
        return "laia-compatibility-layer";
    }

    @Override
    public boolean has(String playerName, double amount) {
        return has(offlinePlayer(playerName), amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        final Optional<UniqueAccount> optionalAccount = this.economyProvider.findUniqueAccount(player.getUniqueId());
        if (optionalAccount.isEmpty()) {
            return false;
        }

        return optionalAccount.get().has(BigDecimal.valueOf(amount));
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        // Todo: world support?
        return has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        // Todo: world support?
        return has(player, amount);
    }

    @Override
    public boolean hasAccount(String playerName) {
        return hasAccount(offlinePlayer(playerName));
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return this.economyProvider.uniqueAccountExists(player.getUniqueId());
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        // Todo: world support?
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        // Todo: world support?
        return hasAccount(player);
    }

    @Override
    public boolean hasBankSupport() {
        return this.economyProvider.features().bankAccounts();
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return isBankMember(name, offlinePlayer(playerName));
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final BankAccount account = this.economyProvider.findBankAccount(name).get();
        if (!account.members().contains(player.getUniqueId())) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, player.getName() + " isn't a member of " + name);
        }

        return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return isBankOwner(name, offlinePlayer(playerName));
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        // Requires Bank accounts support
        if (!this.economyProvider.features().bankAccounts()) {
            return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Bank accounts aren't supported");
        }

        // Check if bank account exists
        if (!this.economyProvider.bankAccountExists(name)) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "Account " + name + " doesn't exist");
        }

        final BankAccount account = this.economyProvider.findBankAccount(name).get();
        if (!account.members().contains(player.getUniqueId())) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, player.getName() + " isn't a member of " + name);
        }

        if (account.owner().isEmpty()) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, name + " has no owner");
        }

        if (!account.owner().get().equals(player.getUniqueId())) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, player.getName() + " isn't the owner of " + name);
        }

        return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return withdrawPlayer(offlinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        // Check if user account exists
        if (!this.economyProvider.uniqueAccountExists(player.getUniqueId())) {
            return new EconomyResponse(amount, 0, ResponseType.FAILURE, "User account " + player.getUniqueId() + " doesn't exist");
        }

        final Optional<UniqueAccount> optionalAccount = this.economyProvider.findUniqueAccount(player.getUniqueId());
        final TransactionResult result = optionalAccount.get().withdraw(BigDecimal.valueOf(amount));
        if (!result.isSuccess()) {
            return new EconomyResponse(result.transaction().amount().doubleValue(), result.transaction().account().balance().doubleValue(), ResponseType.FAILURE, result.resultType().name());
        }

        return new EconomyResponse(0, 0, ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        // Todo: world support?
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        // Todo: world support?
        return withdrawPlayer(player, amount);
    }
    
}
