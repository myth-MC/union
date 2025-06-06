package ovh.mythmc.union.economy.v1.currency.option;

import org.jetbrains.annotations.NotNull;

final class CurrencyOptionsBuilderImpl implements CurrencyOptionsBuilder {

    private String worldName;

    @Override
    public @NotNull CurrencyOptionsBuilder worldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    @Override
    public @NotNull CurrencyOptions build() {
        return new CurrencyOptionsImpl(worldName);
    }
    
}
