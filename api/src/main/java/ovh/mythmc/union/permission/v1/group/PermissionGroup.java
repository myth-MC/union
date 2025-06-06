package ovh.mythmc.union.permission.v1.group;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.util.Identified;

public sealed interface PermissionGroup extends Identified<String> permits PermissionGroupImpl {

    static PermissionGroup of(@NotNull String name) {
        return new PermissionGroupImpl(name);
    }
    
}
