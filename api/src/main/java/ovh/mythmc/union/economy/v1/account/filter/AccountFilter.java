package ovh.mythmc.union.economy.v1.account.filter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.BankAccount;
import ovh.mythmc.union.economy.v1.account.UniqueAccount;
import ovh.mythmc.union.economy.v1.account.VirtualAccount;
import ovh.mythmc.union.economy.v1.currency.Currency;

public interface AccountFilter<T extends AccountFilter<T, A, I>, A extends Account<I>, I> {

    static BankAccountFilter bank(@NotNull Set<BankAccount> accounts) {
        return new BankAccountFilterImpl(accounts);
    }

    static AccountFilter<?, UniqueAccount, UUID> unique(@NotNull Set<UniqueAccount> accounts) {
        return new UniqueAccountFilterImpl(accounts);
    }

    static AccountFilter<?, VirtualAccount, String> virtual(@NotNull Set<VirtualAccount> accounts) {
        return new VirtualAccountFilterImpl(accounts);
    }

    @NotNull AccountFilter<T, A, I> defaultCurrency(@NotNull Currency currency);

    @NotNull AccountFilter<T, A, I> displayName(@NotNull Component displayName);

    @NotNull AccountFilter<T, A, I> minBalance(@NotNull Currency currency, @NotNull BigDecimal minBalance);

    @NotNull AccountFilter<T, A, I> maxBalance(@NotNull Currency currency, @NotNull BigDecimal maxBalance);

    @NotNull Set<A> all();

    @NotNull Optional<A> identifier(@NotNull I identifier);

    @NotNull Optional<A> findFirst();

    @NotNull Optional<A> findAny();
    
}
