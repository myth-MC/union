package ovh.mythmc.union.util;

import org.jetbrains.annotations.NotNull;

public interface Identified<T> {

    @NotNull T identifier();
    
}
