package ovh.mythmc.union.metadata.container.result;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ovh.mythmc.union.metadata.node.MetadataNode;

import java.util.Optional;

public sealed interface MetadataModificationResult permits MetadataModificationResultImpl {

    static @NotNull MetadataModificationResult of(@NotNull MetadataNode<?> node, @NotNull Type resultType, @Nullable Component message) {
        return new MetadataModificationResultImpl(node, resultType, message);
    }

    static @NotNull MetadataModificationResult of(@NotNull MetadataNode<?> node, @NotNull Type resultType) {
        return of(node, resultType, null);
    }

    static @NotNull MetadataModificationResult notSupported(@NotNull MetadataNode<?> node, @Nullable Component message) {
        return of(node, Type.NOT_SUPPORTED, message);
    }

    static @NotNull MetadataModificationResult notSupported(@NotNull MetadataNode<?> node) {
        return notSupported(node, null);
    }

    static @NotNull MetadataModificationResult failure(@NotNull MetadataNode<?> node, @Nullable Component message) {
        return of(node, Type.FAILURE, message);
    }

    static @NotNull MetadataModificationResult failure(@NotNull MetadataNode<?> node) {
        return failure(node, null);
    }

    static @NotNull MetadataModificationResult success(@NotNull MetadataNode<?> node, @Nullable Component message) {
        return of(node, Type.SUCCESS, message);
    }

    static @NotNull MetadataModificationResult success(@NotNull MetadataNode<?> node) {
        return success(node, null);
    }

    @NotNull MetadataNode<?> node();

    @NotNull Type resultType();

    @NotNull Optional<Component> message();

    enum Type {

        NOT_SUPPORTED,
        FAILURE,
        SUCCESS

    }

}
