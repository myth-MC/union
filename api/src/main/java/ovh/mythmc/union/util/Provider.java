package ovh.mythmc.union.util;

import org.jetbrains.annotations.NotNull;

public interface Provider<F> {

    @NotNull F features();

    @NotNull String name();

    boolean enabled();
    
}
