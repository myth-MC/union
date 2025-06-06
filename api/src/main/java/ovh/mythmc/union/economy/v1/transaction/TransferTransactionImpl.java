package ovh.mythmc.union.economy.v1.transaction;

import java.math.BigDecimal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.currency.Currency;

final class TransferTransactionImpl extends TransactionImpl implements TransferTransaction {

    private final Account<?> toAccount;

    TransferTransactionImpl(@NotNull Account<?> toAccount, @NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency, @Nullable String worldName) {
        super(account, amount, currency, worldName);
        this.toAccount = toAccount;
    }
    
    @Override
    public @NotNull Account<?> toAccount() {
        return this.toAccount;
    }

    static final class Builder extends TransactionBuilder<TransferTransaction> {

        private final Account<?> toAccount;

        protected Builder(@NotNull Account<?> toAccount, @NotNull Account<?> account, @NotNull BigDecimal amount, @NotNull Currency currency) {
            super(account, amount, currency);
            this.toAccount = toAccount;
        }
        
        @Override
        public TransferTransaction build() {
            return new TransferTransactionImpl(toAccount, account, amount, currency, worldName);
        }

    }

}
