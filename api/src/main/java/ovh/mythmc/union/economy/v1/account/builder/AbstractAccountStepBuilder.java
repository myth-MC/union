package ovh.mythmc.union.economy.v1.account.builder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.transaction.Transaction;
import ovh.mythmc.union.economy.v1.transaction.TransferTransaction;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;

abstract class AbstractAccountStepBuilder<A extends Account<I>, I> 
    implements AccountStepBuilder,
               AccountStepBuilder.IdentifierStep<A, I>,
               AccountStepBuilder.DisplayNameStep<A, I>,
               AccountStepBuilder.DefaultCurrencyStep<A, I>,
               AccountStepBuilder.CurrenciesStep<A, I>,
               AccountStepBuilder.OptionsStep<A, I>,
               AccountStepBuilder.DepositStep<A, I>,
               AccountStepBuilder.WithdrawStep<A, I>,
               AccountStepBuilder.TransferStep<A, I>,
               AccountStepBuilder.BuildStep<A, I>
{

    protected I identifier;

    protected Component displayName;

    protected Currency defaultCurrency;

    protected Supplier<Map<Currency, BigDecimal>> currenciesSupplier;

    protected AccountOptions options;

    protected Function<Transaction, TransactionResult> depositFunction;

    protected Function<Transaction, TransactionResult> withdrawFunction;

    protected Function<TransferTransaction, TransactionResult> transferFunction;

    @Override
    public @NotNull DisplayNameStep<A, I> identifier(@NotNull I identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public @NotNull DefaultCurrencyStep<A, I> displayName(@NotNull Component displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public @NotNull CurrenciesStep<A, I> defaultCurrency(@NotNull Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
        return this;
    }

    @Override
    public @NotNull OptionsStep<A, I> currencies(@NotNull Supplier<Map<Currency, BigDecimal>> currenciesSupplier) {
        this.currenciesSupplier = currenciesSupplier;
        return this;
    }

    @Override
    public @NotNull DepositStep<A, I> options(@NotNull AccountOptions options) {
        this.options = options;
        return this;
    }

    @Override
    public @NotNull WithdrawStep<A, I> deposit(@NotNull Function<Transaction, TransactionResult> depositFunction) {
        this.depositFunction = depositFunction;
        return this;
    }

    @Override
    public @NotNull TransferStep<A, I> withdraw(@NotNull Function<Transaction, TransactionResult> withdrawFunction) {
        this.withdrawFunction = withdrawFunction;
        return this;
    }

    @Override
    public @NotNull BuildStep<A, I> transfer(@NotNull Function<TransferTransaction, TransactionResult> transferFunction) {
        this.transferFunction = transferFunction;
        return this;
    }
    
}
