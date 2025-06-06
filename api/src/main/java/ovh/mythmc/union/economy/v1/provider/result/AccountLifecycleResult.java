package ovh.mythmc.union.economy.v1.provider.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;


public sealed interface AccountLifecycleResult<T extends Account<I>, I> permits AccountLifecycleResultImpl {

    static <T extends Account<I>, I> AccountLifecycleResult<T, I> notSupported(@NotNull I identifier, @NotNull String feature) {
        return new AccountLifecycleResultImpl<T,I>(identifier, Type.NOT_SUPPORTED, null, Component.text("Account operation not supported: " + feature));
    }

    static <T extends Account<I>, I> AccountLifecycleResult<T, I> notSupported(@NotNull I identifier) {
        return new AccountLifecycleResultImpl<T,I>(identifier, Type.NOT_SUPPORTED, null, Component.text("Account operation not supported"));
    }

    static <T extends Account<I>, I> AccountLifecycleResult<T, I> failure(@NotNull I identifier, @Nullable Component message) {
        return new AccountLifecycleResultImpl<>(identifier, Type.FAILURE, null, message);
    }

    static <T extends Account<I>, I> AccountLifecycleResult<T, I> failure(@NotNull I identifier) {
        return failure(identifier, null);
    }

    static <T extends Account<I>, I> AccountLifecycleResult<T, I> success(@NotNull T account) {
        return new AccountLifecycleResultImpl<>(account.identifier(), Type.SUCCESS, account, null);
    }

    @NotNull I identifier();

    @NotNull Type type();

    @NotNull Optional<T> account();

    @NotNull Optional<Component> message();

    enum Type {

        NOT_SUPPORTED,
        FAILURE,
        SUCCESS

    }

    default boolean isSuccess() {
        return this.type().equals(Type.SUCCESS);
    }
    
}
