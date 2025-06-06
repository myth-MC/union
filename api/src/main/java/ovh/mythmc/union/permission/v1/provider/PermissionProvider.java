package ovh.mythmc.union.permission.v1.provider;

import java.util.Optional;
import java.util.UUID;
import java.util.ServiceLoader.Provider;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.permission.v1.entity.PermissionEntity;
import ovh.mythmc.union.permission.v1.entity.PermissionUser;
import ovh.mythmc.union.permission.v1.provider.feature.PermissionFeatures;

public interface PermissionProvider<T> extends Provider<PermissionFeatures> {

    @NotNull Optional<PermissionEntity> commandSender(@NotNull T obj);

    @NotNull Optional<PermissionUser> user(@NotNull UUID uuid);
    
}
