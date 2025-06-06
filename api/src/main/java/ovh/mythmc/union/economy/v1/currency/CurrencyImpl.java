package ovh.mythmc.union.economy.v1.currency;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;

final class CurrencyImpl implements Currency {

    private final int fractionalDigits;

    private final String name;

    private final Component displayNameSingular;

    private final Component displayNamePlural;

    private final Function<BigDecimal, Component> formatter;

    private final Component symbol;

    private final String worldName;

    CurrencyImpl(int fractionalDigits, @NotNull String name, @NotNull Component displayNameSingular, @NotNull Component displayNamePlural, @NotNull Function<BigDecimal, Component> formatter, @NotNull Component symbol, @Nullable String worldName) {
        this.fractionalDigits = fractionalDigits;
        this.name = name;
        this.displayNameSingular = displayNameSingular;
        this.displayNamePlural = displayNamePlural;
        this.formatter = formatter;
        this.symbol = symbol;
        this.worldName = worldName;
    }

    @Override
    public int fractionalDigits() {
        return this.fractionalDigits;
    }

    @Override
    public @NotNull String name() {
        return this.name;
    }

    @Override
    public @NotNull Component displayNameSingular() {
        return this.displayNameSingular;
    }

    @Override
    public @NotNull Component displayNamePlural() {
        return this.displayNamePlural;
    }

    @Override
    public @NotNull Component format(@NotNull BigDecimal amount) {
        return this.formatter.apply(amount);
    }

    @Override
    public @NotNull Component symbol() {
        return this.symbol;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        CurrencyImpl currency = (CurrencyImpl) object;
        return fractionalDigits == currency.fractionalDigits && Objects.equals(name, currency.name) && Objects.equals(displayNameSingular, currency.displayNameSingular) && Objects.equals(displayNamePlural, currency.displayNamePlural) && Objects.equals(formatter, currency.formatter) && Objects.equals(symbol, currency.symbol) && Objects.equals(worldName, currency.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
