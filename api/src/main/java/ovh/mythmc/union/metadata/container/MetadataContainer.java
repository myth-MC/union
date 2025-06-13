package ovh.mythmc.union.metadata.container;

import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.metadata.container.result.MetadataModificationResult;
import ovh.mythmc.union.metadata.node.MetadataNode;

import java.util.Optional;

public interface MetadataContainer {

    @NotNull <T> Optional<T> requestMetadata(@NotNull MetadataNode<T> node);

    @NotNull <T> MetadataModificationResult addMetadata(@NotNull MetadataNode<T> node, @NotNull T metadata);

    @NotNull <T> MetadataModificationResult removeMetadata(@NotNull MetadataNode<T> node);

    <T> boolean hasMetadata(@NotNull MetadataNode<T> node);

    default @NotNull <T> Optional<T> requestMetadata(@NotNull MetadataNode<T> node, @NotNull T defaultValue) {
        return requestMetadata(node).or(() -> Optional.of(defaultValue));
    }

}
