package ovh.mythmc.union.economy.v1.provider.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.economy.v1.account.Account;

final class AccountLifecycleResultImpl<T extends Account<I>, I> implements AccountLifecycleResult<T, I> {

    private final I identifier;

    private final Type type;

    private final T account;

    private final Component message;

    AccountLifecycleResultImpl(@NotNull I identifier, @NotNull Type type, @Nullable T account, @Nullable Component message) {
        this.identifier = identifier;
        this.type = type;
        this.account = account;
        this.message = message;
    }

    @Override
    public @NotNull I identifier() {
        return this.identifier;
    }

    @Override
    public @NotNull Type type() {
        return this.type;
    }

    @Override
    public @NotNull Optional<T> account() {
        return Optional.ofNullable(this.account);
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }
    
}
