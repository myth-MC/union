package ovh.mythmc.union.metadata.container.result;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ovh.mythmc.union.metadata.node.MetadataNode;

import java.util.Optional;

final class MetadataModificationResultImpl implements MetadataModificationResult {

    private final MetadataNode<?> node;

    private final Type resultType;

    private final Component message;

    MetadataModificationResultImpl(@NotNull MetadataNode<?> node, @NotNull Type resultType, @Nullable Component message) {
        this.node = node;
        this.resultType = resultType;
        this.message = message;
    }

    @Override
    public @NotNull MetadataNode<?> node() {
        return this.node;
    }

    @Override
    public @NotNull Type resultType() {
        return this.resultType;
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }

}
