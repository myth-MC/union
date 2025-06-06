package ovh.mythmc.union.economy.v1.provider.feature;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

import org.jetbrains.annotations.ApiStatus.Experimental;

public sealed interface EconomyFeatures permits EconomyFeaturesImpl {

    static EconomyFeatures empty() {
        return new EconomyFeaturesImpl(Set.of());
    }

    static EconomyFeaturesBuilder builder() {
        return new EconomyFeaturesBuilderImpl();
    }

    boolean has(@NotNull EconomyFeature feature);

    default boolean asynchronousTransactions() {
        return has(EconomyFeature.ASYNCHRONOUS_TRANSACTIONS);
    }

    default boolean bankAccounts() {
        return has(EconomyFeature.BANK_ACCOUNTS);
    }

    default boolean multipleCurrencies() {
        return has(EconomyFeature.MULTIPLE_CURRENCIES);
    }

    @Experimental
    default boolean perWorldEconomy() {
        return has(EconomyFeature.PER_WORLD_ECONOMY);
    }

    default boolean virtualAccounts() {
        return has(EconomyFeature.VIRTUAL_ACCOUNTS);
    }

    sealed interface EconomyFeaturesBuilder permits EconomyFeaturesBuilderImpl {

        @NotNull EconomyFeaturesBuilder feature(@NotNull EconomyFeature feature);
    
        @NotNull EconomyFeatures build();
        
    }
    
}
