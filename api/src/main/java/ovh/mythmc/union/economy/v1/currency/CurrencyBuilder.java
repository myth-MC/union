package ovh.mythmc.union.economy.v1.currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public final class CurrencyBuilder {

    private final String name;

    private final Component symbol;

    private int fractionalDigits;

    private Component displayNameSingular;
    
    private Component displayNamePlural;
    
    private Function<BigDecimal, Component> formatter;

    private String worldName;

    CurrencyBuilder(@NotNull String name, @NotNull Component symbol) {
        this.name = name;
        this.symbol = symbol;
        this.fractionalDigits = 2;
        this.displayNameSingular = Component.text(name);
        this.displayNamePlural = Component.text(name);
        this.formatter = (bigDecimal) -> Component.text(new DecimalFormat("#,###.##").format(bigDecimal));
    }

    public CurrencyBuilder fractionalDigits(int fractionalDigits) {
        this.fractionalDigits = fractionalDigits;
        return this;
    }

    public CurrencyBuilder displayNameSingular(@NotNull Component displayNameSingular) {
        this.displayNameSingular = displayNameSingular;
        return this;
    }

    public CurrencyBuilder displayNamePlural(@NotNull Component displayNamePlural) {
        this.displayNamePlural = displayNamePlural;
        return this;
    }

    public CurrencyBuilder formatter(@NotNull Function<BigDecimal, Component> formatter) {
        this.formatter = formatter;
        return this;
    }

    public CurrencyBuilder world(@NotNull String worldName) {
        this.worldName = worldName;
        return this;
    }

    public Currency build() {
        return new CurrencyImpl(fractionalDigits, name, displayNameSingular, displayNamePlural, formatter, symbol, worldName);
    }

}
