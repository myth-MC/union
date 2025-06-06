package ovh.mythmc.union.permission.v1.node.result;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.permission.v1.node.PermissionNode;

final class PermissionNodeResultImpl implements PermissionNodeResult {

    private final PermissionNode node;

    private final PermissionNodeResult.Type resultType;

    private final Component message;

    PermissionNodeResultImpl(@NotNull PermissionNode node, @NotNull PermissionNodeResult.Type resultType, @Nullable Component message) {
        this.node = node;
        this.resultType = resultType;
        this.message = message;
    }

    @Override
    public @NotNull PermissionNode node() {
        return this.node;
    }

    @Override
    public @NotNull PermissionNodeResult.Type resultType() {
        return this.resultType;
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }
    
}
