package ovh.mythmc.union.util;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Buildable<T> {

    T build();

    default @NotNull Optional<T> buildOptional() {
        return Optional.of(build());
    }
    
}
