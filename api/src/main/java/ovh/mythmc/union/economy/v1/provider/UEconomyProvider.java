package ovh.mythmc.union.economy.v1.provider;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.provider.feature.EconomyFeatures;
import ovh.mythmc.union.economy.v1.transaction.Transaction;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;

interface UEconomyProvider {

    @NotNull Currency defaultCurrency();

    @NotNull Set<Currency> currencies();

    @NotNull Set<Account<?>> accounts();

    @NotNull EconomyFeatures features();

    @NotNull TransactionResult transact(@NotNull Transaction transaction);

    @NotNull <A extends Account<I>, I> Optional<A> createAccount(@NotNull Class<A> type, @NotNull I identifier, @Nullable Object... args);

    <A extends Account<I>, I> boolean deleteAccount(@NotNull Class<A> type, @NotNull I identifier);

    /*
     * Helper methods
     */

    default Optional<Currency> currencyByName(@NotNull String currencyName) {
        return currencies().stream().filter(currency -> currency.name().equalsIgnoreCase(currencyName)).findAny();
    }

    default <A extends Account<I>, I> boolean accountExists(@NotNull Class<A> type, @NotNull I identifier) {
        return findAccount(type, identifier).isPresent();
    }

    @SuppressWarnings("unchecked")
    default <A extends Account<I>, I> Optional<A> findAccount(@NotNull Class<A> type, @NotNull I identifier) {
        return accounts().stream()
            .filter(account -> account.getClass().isAssignableFrom(type) && account.identifier().equals(identifier))
            .map(account -> (A) account)
            .findAny();
    }

    default <A extends Account<I>, I> Optional<A> findOrCreateAccount(@NotNull Class<A> type, @NotNull I identifier, Object... args) {
        return findAccount(type, identifier).or(() -> createAccount(type, identifier, args));
    }

    @SuppressWarnings("unchecked")
    default <A extends Account<?>> Set<A> accounts(@NotNull Class<A> type) {
        return this.accounts().stream()
            .filter(account -> account.getClass().isAssignableFrom(type))
            .map(account -> (A) account)
            .collect(Collectors.toSet());
    }
    
}
