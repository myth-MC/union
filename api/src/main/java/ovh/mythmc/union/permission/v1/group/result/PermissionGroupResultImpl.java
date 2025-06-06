package ovh.mythmc.union.permission.v1.group.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.permission.v1.group.PermissionGroup;

final class PermissionGroupResultImpl implements PermissionGroupResult {

    private final PermissionGroup group;

    private final PermissionGroupResult.Type resultType;

    private final Component message;

    PermissionGroupResultImpl(@NotNull PermissionGroup group, @NotNull PermissionGroupResult.Type resulType, @Nullable Component message) {
        this.group = group;
        this.resultType = resulType;
        this.message = message;
    }

    @Override
    public @NotNull PermissionGroup group() {
        return this.group;
    }

    @Override
    public @NotNull PermissionGroupResult.Type resultType() {
        return this.resultType;
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }
    
}
