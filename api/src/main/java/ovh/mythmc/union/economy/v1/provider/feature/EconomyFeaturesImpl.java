package ovh.mythmc.union.economy.v1.provider.feature;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

final class EconomyFeaturesImpl implements EconomyFeatures {

    private final Set<EconomyFeature> features;
    
    EconomyFeaturesImpl(@NotNull Set<EconomyFeature> features) {
        this.features = features;
    }

    @Override
    public boolean has(@NotNull EconomyFeature feature) {
        return this.features.contains(feature);
    }

}
