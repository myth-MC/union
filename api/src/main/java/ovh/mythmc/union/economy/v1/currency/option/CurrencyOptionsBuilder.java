package ovh.mythmc.union.economy.v1.currency.option;

import org.jetbrains.annotations.NotNull;

public sealed interface CurrencyOptionsBuilder permits CurrencyOptionsBuilderImpl {

    @NotNull CurrencyOptionsBuilder worldName(String worldName);

    @NotNull CurrencyOptions build();
    
}
