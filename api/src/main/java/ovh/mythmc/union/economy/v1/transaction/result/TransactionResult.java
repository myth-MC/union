package ovh.mythmc.union.economy.v1.transaction.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.transaction.Transaction;

public sealed interface TransactionResult permits TransactionResultImpl {

    static TransactionResult of(@NotNull Transaction transaction, @NotNull TransactionResult.Type resultType, @Nullable Component message) {
        return new TransactionResultImpl(transaction, resultType, message);
    }

    static TransactionResult of(@NotNull Transaction transaction, @NotNull TransactionResult.Type resultType) {
        return of(transaction, resultType, null);
    }

    static TransactionResult noFunds(@NotNull Transaction transaction, @Nullable Component message) {
        return of(transaction, TransactionResult.Type.NO_FUNDS, message);
    }

    static TransactionResult noFunds(@NotNull Transaction transaction) {
        return noFunds(transaction, null);
    }

    static TransactionResult noSpace(@NotNull Transaction transaction, @Nullable Component message) {
        return of(transaction, TransactionResult.Type.NO_SPACE, message);
    }

    static TransactionResult noSpace(@NotNull Transaction transaction) {
        return noSpace(transaction, null);
    }

    static TransactionResult failure(@NotNull Transaction transaction, @Nullable Component message) {
        return of(transaction, TransactionResult.Type.FAILURE, message);
    } 

    static TransactionResult failure(@NotNull Transaction transaction) {
        return failure(transaction, null);
    }

    static TransactionResult success(@NotNull Transaction transaction, @Nullable Component message) {
        return of(transaction, TransactionResult.Type.SUCCESS, message);
    }

    static TransactionResult success(@NotNull Transaction transaction) {
        return success(transaction, null);
    }
    
    @NotNull Transaction transaction();

    @NotNull TransactionResult.Type resultType();

    @NotNull Optional<Component> message();

    default boolean isSuccess() {
        return resultType().equals(TransactionResult.Type.SUCCESS);
    }

    enum Type {

        NO_FUNDS,
        NO_SPACE,
        FAILURE,
        SUCCESS
        
    }

}
