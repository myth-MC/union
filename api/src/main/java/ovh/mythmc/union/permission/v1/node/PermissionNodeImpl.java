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
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof PermissionNodeImpl))
            return false;

        PermissionNodeImpl permissionNodeImpl = (PermissionNodeImpl) obj;
        return Objects.equals(this.identifier, permissionNodeImpl.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
    
}
