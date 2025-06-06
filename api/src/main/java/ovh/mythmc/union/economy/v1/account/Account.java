package ovh.mythmc.union.economy.v1.account;

import java.math.BigDecimal;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;
import ovh.mythmc.union.util.Identified;

public sealed interface Account<I> extends Identified<I> permits UniqueAccount, VirtualAccount {

    @NotNull I identifier();

    @NotNull Component displayName();

    @NotNull Currency defaultCurrency();

    @NotNull Map<Currency, BigDecimal> currencies();

    @Experimental @NotNull AccountOptions options();

    /*
     * Helper methods
     */
    default @NotNull BigDecimal balance(@NotNull Currency currency) {
        return currencies().get(currency);
    }

    default @NotNull BigDecimal balance() {
        return balance(defaultCurrency());
    }
    
    @NotNull TransactionResult deposit(@NotNull Currency currency, @NotNull BigDecimal amount);

    default @NotNull TransactionResult deposit(@NotNull BigDecimal amount) {
        return deposit(defaultCurrency(), amount);
    }

    @NotNull TransactionResult withdraw(@NotNull Currency currency, @NotNull BigDecimal amount);

    default @NotNull TransactionResult withdraw(@NotNull BigDecimal amount) {
        return withdraw(defaultCurrency(), amount);
    }

    @NotNull TransactionResult transfer(@NotNull Account<?> account, @NotNull Currency currency, @NotNull BigDecimal amount);

    default @NotNull TransactionResult transfer(@NotNull Account<?> account, @NotNull BigDecimal amount) {
        return transfer(account, defaultCurrency(), amount);
    }

    default boolean has(@NotNull Currency currency, @NotNull BigDecimal amount) {
        return this.balance(currency).compareTo(amount) >= 0;
    }

    default boolean has(@NotNull BigDecimal amount) {
        return has(defaultCurrency(), amount);
    }
    
}
