package ovh.mythmc.union.economy.v1.account.option;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

public sealed interface BankAccountOptions extends AccountOptions permits BankAccountOptionsImpl {

    static BankAccountOptions empty() {
        return builder().build();
    }

    static BankAccountOptionsBuilder builder() {
        return new BankAccountOptionsBuilderImpl();
    }

    @NotNull Optional<UUID> owner();

    @NotNull Set<UUID> members();

    sealed interface BankAccountOptionsBuilder extends AccountOptionsBuilder<BankAccountOptions> permits BankAccountOptionsBuilderImpl {
        
        @NotNull BankAccountOptionsBuilder owner(UUID owner);

        @NotNull BankAccountOptionsBuilder members(@NotNull Collection<UUID> members);

        default BankAccountOptionsBuilder members(@NotNull UUID... members) {
            return members(Arrays.asList(members));
        }

    }
    
}
