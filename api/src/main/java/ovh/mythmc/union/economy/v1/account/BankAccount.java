package ovh.mythmc.union.economy.v1.account;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

import ovh.mythmc.union.economy.v1.account.option.BankAccountOptions;

@Experimental
public interface BankAccount extends VirtualAccount {

    @NotNull BankAccountOptions options();

    @NotNull Optional<UUID> owner();

    @NotNull Set<UUID> members();

    default boolean isOwner(@NotNull UUID uniqueId) {
        if (owner().isEmpty()) {
            return false;
        }

        return owner().get().equals(uniqueId);
    }

    default boolean isMember(@NotNull UUID uniqueId) {
        return members().contains(uniqueId);
    }
    
}
