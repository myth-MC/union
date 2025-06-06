package ovh.mythmc.union.permission.v1.entity;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.node.PermissionNode;
import ovh.mythmc.union.permission.v1.node.result.PermissionNodeResult;

public interface PermissionEntity {

    @NotNull PermissionNodeResult has(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options);

    @NotNull PermissionNodeResult add(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options);

    @NotNull PermissionNodeResult addTransient(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options);

    @NotNull PermissionNodeResult remove(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options);

    @NotNull PermissionNodeResult removeTransient(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options);
    
    default @NotNull PermissionNodeResult has(@NotNull PermissionNode permission) {
        return has(permission, PermissionQueryOptions.empty());
    }

    default @NotNull PermissionNodeResult add(@NotNull PermissionNode permission) {
        return add(permission, PermissionQueryOptions.empty());
    }

    default @NotNull PermissionNodeResult addTransient(@NotNull PermissionNode permission) {
        return addTransient(permission, PermissionQueryOptions.empty());
    }

    default @NotNull PermissionNodeResult remove(@NotNull PermissionNode permission) {
        return remove(permission, PermissionQueryOptions.empty());
    }

    default @NotNull PermissionNodeResult removeTransient(@NotNull PermissionNode permission) {
        return removeTransient(permission, PermissionQueryOptions.empty());
    }

}
