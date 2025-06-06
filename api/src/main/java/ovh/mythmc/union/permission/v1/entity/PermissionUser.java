package ovh.mythmc.union.permission.v1.entity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.group.PermissionGroup;
import ovh.mythmc.union.permission.v1.group.result.PermissionGroupResult;
import ovh.mythmc.union.util.Identified;

public interface PermissionUser extends PermissionEntity, Identified<UUID> {

    @NotNull Set<PermissionGroup> groups(@NotNull PermissionQueryOptions options);

    @NotNull Optional<PermissionGroup> primaryGroup(@NotNull PermissionQueryOptions options);

    @NotNull PermissionGroupResult addGroup(@NotNull PermissionGroup group, @NotNull PermissionQueryOptions options);

    @NotNull PermissionGroupResult removeGroup(@NotNull PermissionGroup group, @NotNull PermissionQueryOptions options);

    default @NotNull Set<PermissionGroup> groups() {
        return groups(PermissionQueryOptions.empty());
    }

    default @NotNull Optional<PermissionGroup> primaryGroup() {
        return primaryGroup(PermissionQueryOptions.empty());
    }

    default @NotNull PermissionGroupResult addGroup(@NotNull PermissionGroup group) {
        return addGroup(group, PermissionQueryOptions.empty());
    }

    default @NotNull PermissionGroupResult removeGroup(@NotNull PermissionGroup group) {
        return removeGroup(group, PermissionQueryOptions.empty());
    }

    default boolean hasGroup(@NotNull PermissionGroup group, @NotNull PermissionQueryOptions options) {
        return groups(options).contains(group);
    }

    default boolean hasGroup(@NotNull PermissionGroup group) {
        return hasGroup(group, PermissionQueryOptions.empty());
    }
    
}
