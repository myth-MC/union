package ovh.mythmc.union.permission.v1.group;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

final class PermissionGroupImpl implements PermissionGroup {

    private final String identifier;

    PermissionGroupImpl(@NotNull String identifier) {
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

        if (!(obj instanceof PermissionGroupImpl))
            return false;

        PermissionGroupImpl permissionGroupImpl = (PermissionGroupImpl) obj;
        return Objects.equals(this.identifier, permissionGroupImpl.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identifier);
    }
    
}
