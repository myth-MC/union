package ovh.mythmc.union.economy.v1.account.option;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

public sealed interface SharedAccountOptions extends AccountOptions permits SharedAccountOptionsImpl {

    static SharedAccountOptions empty() {
        return builder().build();
    }

    static SharedAccountOptionsBuilder builder() {
        return new SharedAccountOptionsBuilderImpl();
    }

    @NotNull Optional<UUID> owner();

    @NotNull Set<UUID> members();

    sealed interface SharedAccountOptionsBuilder extends AccountOptionsBuilder<SharedAccountOptions> permits SharedAccountOptionsBuilderImpl {
        
        @NotNull SharedAccountOptionsBuilder owner(UUID owner);

        @NotNull SharedAccountOptionsBuilder members(@NotNull Collection<UUID> members);

        default SharedAccountOptionsBuilder members(@NotNull UUID... members) {
            return members(Arrays.asList(members));
        }

    }
    
}
