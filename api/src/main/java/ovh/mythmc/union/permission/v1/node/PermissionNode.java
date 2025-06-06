package ovh.mythmc.union.permission.v1.node;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.util.Identified;

public sealed interface PermissionNode extends Identified<String> permits PermissionNodeImpl {

    static PermissionNode of(@NotNull String name) {
        return new PermissionNodeImpl(name);
    }
    
}
