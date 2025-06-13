package ovh.mythmc.union.chat.v1.entity.query;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ChatQueryOptions {

    static @NotNull ChatQueryOptions empty() {
        return builder().build();
    }

    static @NotNull ChatQueryOptions.Builder builder() {
        return new ChatQueryOptionsImpl.BuilderImpl();
    }

    @NotNull Optional<String> worldName();

    interface Builder {

        @NotNull Builder worldName(String worldName);

        @NotNull ChatQueryOptions build();

    }

}
