package ovh.mythmc.union.permission.v1.entity.query;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class PermissionQueryOptionsImpl implements PermissionQueryOptions {

    private final String worldName;

    private PermissionQueryOptionsImpl(@Nullable String worldName) {
        this.worldName = worldName;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }

    static final class BuilderImpl implements PermissionQueryOptions.Builder {

        private String worldName;

        @Override
        public @NotNull Builder worldName(String worldName) {
            this.worldName = worldName;
            return this;
        }

        @Override
        public @NotNull PermissionQueryOptions build() {
            return new PermissionQueryOptionsImpl(worldName);
        }

    }
    
}
