package ovh.mythmc.union.permission.v1.node;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

final class PermissionNodeImpl implements PermissionNode {

    private final String identifier;

    PermissionNodeImpl(@NotNull String identifier) {
        this.identifier = identifier;
    }

    @Override
    public @NotNull String identifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        PermissionNodeImpl that = (PermissionNodeImpl) object;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
    
}
