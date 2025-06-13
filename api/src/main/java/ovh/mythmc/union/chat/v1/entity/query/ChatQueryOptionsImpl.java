package ovh.mythmc.union.chat.v1.entity.query;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

final class ChatQueryOptionsImpl implements ChatQueryOptions {

    private final String worldName;

    private ChatQueryOptionsImpl(@Nullable String worldName) {
        this.worldName = worldName;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }

    static final class BuilderImpl implements ChatQueryOptions.Builder {

        private String worldName;

        @Override
        public @NotNull Builder worldName(String worldName) {
            this.worldName = worldName;
            return this;
        }

        @Override
        public @NotNull ChatQueryOptions build() {
            return new ChatQueryOptionsImpl(worldName);
        }
    }

}
