package ovh.mythmc.union.util;

import org.jetbrains.annotations.NotNull;

public interface Buildable<T> {

    @NotNull T build();
    
}
