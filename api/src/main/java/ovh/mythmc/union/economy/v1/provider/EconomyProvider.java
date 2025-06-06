package ovh.mythmc.union.economy.v1.provider;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.BankAccount;
import ovh.mythmc.union.economy.v1.account.UniqueAccount;
import ovh.mythmc.union.economy.v1.account.VirtualAccount;
import ovh.mythmc.union.economy.v1.account.filter.AccountFilter;
import ovh.mythmc.union.economy.v1.account.filter.BankAccountFilter;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.account.option.BankAccountOptions;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.provider.feature.EconomyFeatures;
import ovh.mythmc.union.economy.v1.provider.result.AccountLifecycleResult;
import ovh.mythmc.union.util.Provider;

public interface EconomyProvider extends Provider<EconomyFeatures> {

    @NotNull Currency defaultCurrency();

    @NotNull Set<Currency> currencies();

    @NotNull Set<Account<?>> accounts();
    
    @NotNull AccountLifecycleResult<BankAccount, String> createBankAccount(@NotNull String identifier, @NotNull BankAccountOptions options);

    @NotNull AccountLifecycleResult<UniqueAccount, UUID> createUniqueAccount(@NotNull UUID identifier, @NotNull AccountOptions options);

    @NotNull AccountLifecycleResult<VirtualAccount, String> createVirtualAccount(@NotNull String identifier, @NotNull AccountOptions options);

    @NotNull AccountLifecycleResult<BankAccount, String> deleteBankAccount(@NotNull String identifier);

    @NotNull AccountLifecycleResult<UniqueAccount, UUID> deleteUniqueAccount(@NotNull UUID uuid);

    @NotNull AccountLifecycleResult<VirtualAccount, String> deleteVirtualAccount(@NotNull String identifier);

    /*
     * Helper methods
     */

    default @NotNull AccountLifecycleResult<BankAccount, String> createBankAccount(@NotNull String identifier) {
        return createBankAccount(identifier, BankAccountOptions.empty());
    }

    default @NotNull AccountLifecycleResult<UniqueAccount, UUID> createUniqueAccount(@NotNull UUID identifier) {
        return createUniqueAccount(identifier, AccountOptions.empty());
    }

    default @NotNull AccountLifecycleResult<VirtualAccount, String> createVirtualAccount(@NotNull String identifier) {
        return createVirtualAccount(identifier, AccountOptions.empty());
    }

    default Optional<Currency> currencyByName(@NotNull String currencyName) {
        return currencies().stream().filter(currency -> currency.name().equalsIgnoreCase(currencyName)).findAny();
    }

    default BankAccountFilter filterBankAccounts() {
        return AccountFilter.bank(bankAccounts());
    }

    default AccountFilter<?, UniqueAccount, UUID> filterUniqueAccounts() {
        return AccountFilter.unique(uniqueAccounts());
    }

    default AccountFilter<?, VirtualAccount, String> filterVirtualAccounts() {
        return AccountFilter.virtual(virtualAccounts());
    }

    @SuppressWarnings("unchecked")
    default <A extends Account<I>, I> Optional<A> findAccount(@NotNull Class<A> type, @NotNull I identifier) {
        return accounts().stream()
            .filter(account -> account.getClass().isAssignableFrom(type) && account.identifier().equals(identifier))
            .map(account -> (A) account)
            .findAny();
    }

    default Optional<BankAccount> findBankAccount(@NotNull String identifier) {
        return findAccount(BankAccount.class, identifier);
    }

    default Optional<UniqueAccount> findUniqueAccount(@NotNull UUID identifier) {
        return findAccount(UniqueAccount.class, identifier);
    }

    default Optional<VirtualAccount> findVirtualAccount(@NotNull String identifier) {
        return findAccount(VirtualAccount.class, identifier);
    }

    default <A extends Account<I>, I> boolean accountExists(@NotNull Class<A> type, @NotNull I identifier) {
        return findAccount(type, identifier).isPresent();
    }

    @SuppressWarnings("unchecked")
    default <A extends Account<?>> Set<A> accounts(@NotNull Class<A> type) {
        return this.accounts().stream()
            .filter(account -> account.getClass().isAssignableFrom(type))
            .map(account -> (A) account)
            .collect(Collectors.toSet());
    }

    default Set<UniqueAccount> uniqueAccounts() {
        return accounts(UniqueAccount.class);
    }

    default Set<VirtualAccount> virtualAccounts() {
        return accounts(VirtualAccount.class);
    }

    default Set<BankAccount> bankAccounts() {
        return accounts(BankAccount.class);
    }

    default boolean uniqueAccountExists(@NotNull UUID uniqueId) {
        return accountExists(UniqueAccount.class, uniqueId);
    }

    default boolean virtualAccountExists(@NotNull String identifier) {
        return accountExists(VirtualAccount.class, identifier);
    }

    default boolean bankAccountExists(@NotNull String identifier) {
        return accountExists(BankAccount.class, identifier);
    }

}
