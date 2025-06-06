package ovh.mythmc.union.economy.v1.account.builder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;
import ovh.mythmc.union.economy.v1.account.UniqueAccount;
import ovh.mythmc.union.economy.v1.account.option.AccountOptions;
import ovh.mythmc.union.economy.v1.currency.Currency;
import ovh.mythmc.union.economy.v1.transaction.Transaction;
import ovh.mythmc.union.economy.v1.transaction.result.TransactionResult;

final class UniqueAccountStepBuilderImpl extends AbstractAccountStepBuilder<UniqueAccount, UUID> {

    @Override
    public @NotNull Optional<UniqueAccount> build() {
        final var account = new UniqueAccount() {

            @Override
            public @NotNull UUID identifier() {
                return identifier;
            }

            @Override
            public @NotNull Component displayName() {
                return displayName;
            }

            @Override
            public @NotNull Currency defaultCurrency() {
                return defaultCurrency;
            }

            @Override
            public @NotNull Map<Currency, BigDecimal> currencies() {
                return currenciesSupplier.get();
            }

            @Override
            public @NotNull AccountOptions options() {
                return options;
            }

            @Override
            public @NotNull TransactionResult deposit(@NotNull Currency currency, @NotNull BigDecimal amount) {
                return depositFunction.apply(Transaction.deposit(this, amount, currency));
            }

            @Override
            public @NotNull TransactionResult withdraw(@NotNull Currency currency, @NotNull BigDecimal amount) {
                return withdrawFunction.apply(Transaction.withdraw(this, amount, currency));
            }

            @Override
            public @NotNull TransactionResult transfer(@NotNull Account<?> account, @NotNull Currency currency,
                    @NotNull BigDecimal amount) {
                return transferFunction.apply(Transaction.transfer(this, account, amount, currency));
            }
            
        };

        return Optional.of(account);
    }
    
}
