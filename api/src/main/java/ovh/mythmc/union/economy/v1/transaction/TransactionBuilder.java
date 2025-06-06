package ovh.mythmc.union.economy.v1.transaction;

import java.math.BigDecimal;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;

public sealed abstract class TransactionBuilder<T extends Transaction> permits TransferTransactionImpl.Builder, TransactionImpl.Builder {

    protected final Account<?> account;

    protected final BigDecimal amount;

    protected final Currency currency;

    protected String worldName;

    protected TransactionBuilder(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
        this.account = account;
        this.amount = amount;
        this.currency = currency;
    }

    public TransactionBuilder<T> worldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    public abstract T build();

}
