package ovh.mythmc.union.economy.v1.account.filter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;

abstract class AbstractAccountFilter<T extends AccountFilter<T, A, I>, A extends Account<I>, I> implements AccountFilter<T, A, I> {

    abstract Set<A> accounts();

    @Override
    public @NotNull AccountFilter<T, A, I> defaultCurrency(@NotNull Currency currency) {
        accounts().removeIf(account -> !account.defaultCurrency().equals(currency));
        return this;
    }

    @Override
    public @NotNull AccountFilter<T, A, I> displayName(@NotNull Component displayName) {
        accounts().removeIf(account -> !account.displayName().equals(displayName));
        return this;
    }

    @Override
    public @NotNull AccountFilter<T, A, I> minBalance(@NotNull Currency currency, @NotNull BigDecimal minBalance) {
        accounts().removeIf(account -> !(account.balance().compareTo(minBalance) >= 0));
        return this;
    }

    @Override
    public @NotNull AccountFilter<T, A, I> maxBalance(@NotNull Currency currency, @NotNull BigDecimal maxBalance) {
        accounts().removeIf(account -> !(account.balance().compareTo(maxBalance) <= 0));
        return this;
    }

    @Override
    public @NotNull Set<A> all() {
        return this.accounts();
    }

    @Override
    public @NotNull Optional<A> identifier(@NotNull I identifier) {
        return this.accounts().stream()
            .filter(account -> account.identifier().equals(identifier))
            .findAny();
    }

    @Override
    public @NotNull Optional<A> findFirst() {
        return this.accounts().stream()
            .findFirst();
    }

    @Override
    public @NotNull Optional<A> findAny() {
        return this.accounts().stream()
            .findAny();
    }
    
}
