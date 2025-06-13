package ovh.mythmc.union.metadata.node;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

final class MetadataNodeImpl<T> implements MetadataNode<T> {

    private final String identifier;

    private final Class<T> type;

    MetadataNodeImpl(@NotNull String identifier, @NotNull Class<T> type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public @NotNull String identifier() {
        return this.identifier;
    }

    @Override
    public @NotNull Class<T> type() {
        return this.type;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        MetadataNodeImpl<?> that = (MetadataNodeImpl<?>) object;
        return Objects.equals(identifier, that.identifier) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

}
