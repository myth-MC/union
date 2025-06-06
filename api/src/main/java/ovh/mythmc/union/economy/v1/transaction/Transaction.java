package ovh.mythmc.union.economy.v1.transaction;

import java.math.BigDecimal;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;

public sealed interface Transaction permits TransactionImpl, TransferTransaction {

    static TransactionBuilder<Transaction> simpleBuilder(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
        return new TransactionImpl.Builder(account, amount, currency);
    }

    static TransactionBuilder<TransferTransaction> transferBuilder(@NotNull Account<?> account, @NotNull Account<?> toAccount, @NotNull BigDecimal amount, @NotNull Currency currency) {
        return new TransferTransactionImpl.Builder(toAccount, account, amount, currency);
    }

    static Transaction deposit(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
        return simpleBuilder(account, amount, currency)
            .build();
    }

    static TransferTransaction transfer(@NotNull Account<?> account, @NotNull Account<?> toAccount, @NotNull BigDecimal amount, @NotNull Currency currency) {
        return transferBuilder(account, toAccount, amount, currency)
            .build();
    }

    static Transaction withdraw(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
        return simpleBuilder(account, amount, currency)
            .build();
    }

    @NotNull Account<?> account();

    @NotNull BigDecimal amount();

    @NotNull Currency currency();

    @NotNull Optional<String> worldName();
    
}
