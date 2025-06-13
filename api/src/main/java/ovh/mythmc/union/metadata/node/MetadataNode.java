package ovh.mythmc.union.metadata.node;

import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.util.Identified;

public sealed interface MetadataNode<T> extends Identified<String> permits MetadataNodeImpl {

    static <T> @NotNull MetadataNode<T> of(@NotNull String identifier, @NotNull Class<T> type) {
        return new MetadataNodeImpl<>(identifier, type);
    }

    @NotNull Class<T> type();

}
