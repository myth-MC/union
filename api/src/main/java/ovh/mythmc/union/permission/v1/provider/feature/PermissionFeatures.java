package ovh.mythmc.union.permission.v1.provider.feature;

import org.jetbrains.annotations.NotNull;

public sealed interface PermissionFeatures permits PermissionFeaturesImpl {

    static PermissionFeatures empty() {
        return builder().build();
    }

    static PermissionFeaturesBuilder builder() {
        return new PermissionFeaturesImpl.PermissionFeaturesBuilderImpl();
    }

    boolean has(@NotNull PermissionFeature feature);

    default boolean groupSupport() {
        return has(PermissionFeature.GROUP_SUPPORT);
    }

    default boolean superPermsCompatibility() {
        return has(PermissionFeature.SUPER_PERMS_COMPATIBILITY);
    }

    default boolean worldSupport() {
        return has(PermissionFeature.WORLD_SUPPORT);
    }

    sealed interface PermissionFeaturesBuilder permits PermissionFeaturesImpl.PermissionFeaturesBuilderImpl {

        @NotNull PermissionFeaturesBuilder feature(@NotNull PermissionFeature feature);

        @NotNull PermissionFeatures build();

    }
    
}
