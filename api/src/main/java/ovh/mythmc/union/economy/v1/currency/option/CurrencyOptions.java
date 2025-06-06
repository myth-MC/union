package ovh.mythmc.union.economy.v1.currency.option;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public sealed interface CurrencyOptions permits CurrencyOptionsImpl {

    static CurrencyOptionsBuilder builder() {
        return new CurrencyOptionsBuilderImpl();
    }

    @NotNull Optional<String> worldName();
    
}
