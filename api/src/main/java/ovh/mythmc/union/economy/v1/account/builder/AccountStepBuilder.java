package ovh.mythmc.union.economy.v1.account.builder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.UniqueAccount;
import ovh.mythmc.union.economy.v1.account.VirtualAccount;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.transaction.Transaction;
import ovh.mythmc.union.economy.v1.transaction.TransferTransaction;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;

@Experimental
public interface AccountStepBuilder {

    static IdentifierStep<UniqueAccount, UUID> unique() {
        return new UniqueAccountStepBuilderImpl();
    }

    static IdentifierStep<VirtualAccount, String> virtual() {
        return new VirtualAccountStepBuilderImpl();
    }

    interface IdentifierStep<A extends Account<I>, I> {

        @NotNull DisplayNameStep<A, I> identifier(@NotNull I identifier);

    }

    interface DisplayNameStep<A extends Account<I>, I> {

        @NotNull DefaultCurrencyStep<A, I> displayName(@NotNull Component displayName);

    }

    interface DefaultCurrencyStep<A extends Account<I>, I> {

        @NotNull CurrenciesStep<A, I> defaultCurrency(@NotNull Currency defaultCurrency);

    }

    interface CurrenciesStep<A extends Account<I>, I> {

        @NotNull OptionsStep<A, I> currencies(@NotNull Supplier<Map<Currency, BigDecimal>> currenciesSupplier);

    }

    interface OptionsStep<A extends Account<I>, I> {

        @NotNull DepositStep<A, I> options(@NotNull AccountOptions options);

        default DepositStep<A, I> noOptions() {
            return options(AccountOptions.empty());
        }

    }

    interface DepositStep<A extends Account<I>, I> {

        @NotNull WithdrawStep<A, I> deposit(@NotNull Function<Transaction, TransactionResult> depositFunction);

    }

    interface WithdrawStep<A extends Account<I>, I> {

        @NotNull TransferStep<A, I> withdraw(@NotNull Function<Transaction, TransactionResult> withdrawFunction);

    }

    interface TransferStep<A extends Account<I>, I> {

        @NotNull BuildStep<A, I> transfer(@NotNull Function<TransferTransaction, TransactionResult> transferFunction);

    }

    interface BuildStep<A extends Account<I>, I> {

        @NotNull Optional<A> build();

    }
    
}
