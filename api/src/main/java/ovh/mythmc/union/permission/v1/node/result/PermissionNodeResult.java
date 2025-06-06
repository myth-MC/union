package ovh.mythmc.union.permission.v1.node.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.permission.v1.node.PermissionNode;

public sealed interface PermissionNodeResult permits PermissionNodeResultImpl {

    static PermissionNodeResult of(@NotNull PermissionNode node, @NotNull PermissionNodeResult.Type resultType, @Nullable Component message) {
        return new PermissionNodeResultImpl(node, resultType, message);
    }

    static PermissionNodeResult of(@NotNull PermissionNode node, @NotNull PermissionNodeResult.Type resultType) {
        return of(node, resultType, null);
    }

    static PermissionNodeResult unknownNode(@NotNull PermissionNode node, @Nullable Component message) {
        return of(node, PermissionNodeResult.Type.UNKNOWN_NODE, message);
    }

    static PermissionNodeResult unknownNode(@NotNull PermissionNode node) {
        return unknownNode(node, null);
    }

    static PermissionNodeResult failure(@NotNull PermissionNode node, @Nullable Component message) {
        return of(node, PermissionNodeResult.Type.FAILURE, message);
    }

    static PermissionNodeResult failure(@NotNull PermissionNode node) {
        return failure(node, null);
    }

    static PermissionNodeResult success(@NotNull PermissionNode node, @Nullable Component message) {
        return of(node, PermissionNodeResult.Type.SUCCESS, message);
    }

    static PermissionNodeResult success(@NotNull PermissionNode node) {
        return success(node, null);
    }

    @NotNull PermissionNode node();

    @NotNull PermissionNodeResult.Type resultType();

    @NotNull Optional<Component> message();

    default boolean isSuccess() {
        return resultType().equals(PermissionNodeResult.Type.SUCCESS);
    }

    enum Type {

        UNKNOWN_NODE,
        FAILURE,
        SUCCESS

    }
    
}