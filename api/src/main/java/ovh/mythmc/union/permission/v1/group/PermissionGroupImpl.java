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
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        PermissionGroupImpl that = (PermissionGroupImpl) object;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identifier);
    }
    
}
