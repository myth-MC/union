package ovh.mythmc.union.economy.v1.provider.feature;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.provider.feature.EconomyFeatures.EconomyFeaturesBuilder;

final class EconomyFeaturesBuilderImpl implements EconomyFeaturesBuilder {

    private final Set<EconomyFeature> features = new HashSet<>();

    @Override
    public @NotNull EconomyFeaturesBuilder feature(@NotNull EconomyFeature feature) {
        this.features.add(feature);
        return this;
    }

    @Override
    public @NotNull EconomyFeatures build() {
        return new EconomyFeaturesImpl(features);
    }
    
}
