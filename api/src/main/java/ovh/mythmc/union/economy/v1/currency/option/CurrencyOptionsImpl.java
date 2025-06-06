package ovh.mythmc.union.economy.v1.currency.option;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

final class CurrencyOptionsImpl implements CurrencyOptions {

    private final String worldName;

    CurrencyOptionsImpl(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }
    
}
