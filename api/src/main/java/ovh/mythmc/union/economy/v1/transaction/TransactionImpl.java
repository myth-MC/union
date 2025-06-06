package ovh.mythmc.union.economy.v1.transaction;

import java.math.BigDecimal;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;

non-sealed class TransactionImpl implements Transaction {

    private final Account<?> account;

    private final BigDecimal amount;

    private final Currency currency;

    private final String worldName;

    TransactionImpl(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency, @Nullable String worldName) {
        this.account = account;
        this.amount = amount;
        this.currency = currency;
        this.worldName = worldName;
    }

    @Override
    public @NotNull Account<?> account() {
        return this.account;
    }

    @Override
    public @NotNull BigDecimal amount() {
        return this.amount;
    }

    @Override
    public @NotNull Currency currency() {
        return this.currency;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }
    
    static final class Builder extends TransactionBuilder<Transaction> {

        protected Builder(@NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
            super(account, amount, currency);
        }
        
        @Override
        public Transaction build() {
            return new TransactionImpl(account, amount, currency, worldName);
        }

    }

}
