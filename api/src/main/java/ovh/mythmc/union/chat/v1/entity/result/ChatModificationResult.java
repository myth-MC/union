package ovh.mythmc.union.chat.v1.entity.result;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface ChatModificationResult {

    static @NotNull ChatModificationResult of(@NotNull Type type, @Nullable Component message) {
        return new ChatModificationResultImpl(type, message);
    }

    static @NotNull ChatModificationResult of(@NotNull Type type) {
        return of(type, null);
    }

    static @NotNull ChatModificationResult notSupported(@Nullable Component message) {
        return of(Type.NOT_SUPPORTED, message);
    }

    static @NotNull ChatModificationResult notSupported() {
        return notSupported(null);
    }

    static @NotNull ChatModificationResult failure(@Nullable Component message) {
        return of(Type.FAILURE, message);
    }

    static @NotNull ChatModificationResult failure() {
        return failure(null);
    }

    static @NotNull ChatModificationResult success(@Nullable Component message) {
        return of(Type.SUCCESS, message);
    }

    static @NotNull ChatModificationResult success() {
        return success(null);
    }

    @NotNull Type type();

    @NotNull Optional<Component> message();

    enum Type {

        NOT_SUPPORTED,
        FAILURE,
        SUCCESS

    }

}
