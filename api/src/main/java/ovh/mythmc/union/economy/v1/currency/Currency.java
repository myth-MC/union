package ovh.mythmc.union.economy.v1.currency;

import java.math.BigDecimal;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

import net.kyori.adventure.text.Component;

public interface Currency {

    static CurrencyBuilder builder(@NotNull String name, @NotNull Component symbol) {
        return new CurrencyBuilder(name, symbol);
    }
    
    int fractionalDigits();

    @NotNull String name();

    @NotNull Component displayNameSingular();

    @NotNull Component displayNamePlural();

    @NotNull Component format(@NotNull BigDecimal amount);

    @NotNull Component symbol();

    @Experimental @NotNull Optional<String> worldName();

}
