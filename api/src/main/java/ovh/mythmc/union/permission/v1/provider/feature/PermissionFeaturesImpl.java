package ovh.mythmc.union.permission.v1.provider.feature;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

final class PermissionFeaturesImpl implements PermissionFeatures {

    private final Set<PermissionFeature> features;

    private PermissionFeaturesImpl(@NotNull Set<PermissionFeature> features) {
        this.features = features;
    }

    @Override
    public boolean has(@NotNull PermissionFeature feature) {
        return this.features.contains(feature);
    }

    static final class PermissionFeaturesBuilderImpl implements PermissionFeaturesBuilder {

        private final Set<PermissionFeature> features = new HashSet<>();

        @Override
        public @NotNull PermissionFeaturesBuilder feature(@NotNull PermissionFeature feature) {
            this.features.add(feature);
            return this;
        }

        @Override
        public @NotNull PermissionFeatures build() {
            return new PermissionFeaturesImpl(features);
        }

    }

}