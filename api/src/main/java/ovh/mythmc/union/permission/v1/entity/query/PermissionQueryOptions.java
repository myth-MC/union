package ovh.mythmc.union.permission.v1.entity.query;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public sealed interface PermissionQueryOptions permits PermissionQueryOptionsImpl {

    static @NotNull PermissionQueryOptions empty() {
        return builder().build();
    }

    static @NotNull PermissionQueryOptions.Builder builder() {
        return new PermissionQueryOptionsImpl.BuilderImpl();
    }

    @NotNull Optional<String> worldName();

    sealed interface Builder permits PermissionQueryOptionsImpl.BuilderImpl {

        @NotNull Builder worldName(String worldName);

        @NotNull PermissionQueryOptions build();

    }
    
}
