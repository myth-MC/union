package ovh.mythmc.union.permission.v1.group.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.permission.v1.group.PermissionGroup;

public sealed interface PermissionGroupResult permits PermissionGroupResultImpl {

    static PermissionGroupResult of(@NotNull PermissionGroup group, @NotNull PermissionGroupResult.Type resultType, @Nullable Component message) {
        return new PermissionGroupResultImpl(group, resultType, message);
    }

    static PermissionGroupResult of(@NotNull PermissionGroup group, @NotNull PermissionGroupResult.Type resultType) {
        return of(group, resultType, null);
    }

    static PermissionGroupResult unknownGroup(@NotNull PermissionGroup group, @Nullable Component message) {
        return of(group, PermissionGroupResult.Type.UNKNOWN_GROUP, message);
    }

    static PermissionGroupResult unknownGroup(@NotNull PermissionGroup group) {
        return unknownGroup(group, null);
    }

    static PermissionGroupResult failure(@NotNull PermissionGroup group, @Nullable Component message) {
        return of(group, PermissionGroupResult.Type.FAILURE, message);
    }

    static PermissionGroupResult failure(@NotNull PermissionGroup group) {
        return failure(group, null);
    }

    static PermissionGroupResult success(@NotNull PermissionGroup group, @Nullable Component message) {
        return of(group, PermissionGroupResult.Type.SUCCESS, message);
    }

    static PermissionGroupResult success(@NotNull PermissionGroup group) {
        return success(group, null);
    }

    @NotNull PermissionGroup group();

    @NotNull PermissionGroupResult.Type resultType();

    @NotNull Optional<Component> message();

    default boolean isSuccess() {
        return resultType().equals(PermissionGroupResult.Type.SUCCESS);
    }

    enum Type {

        UNKNOWN_GROUP,
        FAILURE,
        SUCCESS

    }
    
}
