package ovh.mythmc.union.economy.v1.transaction.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.transaction.Transaction;

final class TransactionResultImpl implements TransactionResult {

    private final Transaction transaction;

    private final TransactionResult.Type resultType;

    private final Component message;

    TransactionResultImpl(@NotNull Transaction transaction, @NotNull TransactionResult.Type resultType, @Nullable Component message) {
        this.transaction = transaction;
        this.resultType = resultType;
        this.message = message;
    }

    @Override
    public @NotNull Transaction transaction() {
        return this.transaction;
    }

    @Override
    public @NotNull TransactionResult.Type resultType() {
        return this.resultType;
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }
    
}
