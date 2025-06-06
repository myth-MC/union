package ovh.mythmc.union.economy.v1.account.option;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

final class BankAccountOptionsImpl extends AccountOptionsImpl implements BankAccountOptions {

    private final UUID owner;

    private final Set<UUID> members;

    BankAccountOptionsImpl(String worldName, UUID owner, Set<UUID> members) {
        super(worldName);
        this.owner = owner;
        this.members = members;
    }
    
    @Override
    public @NotNull Optional<UUID> owner() {
        return Optional.ofNullable(this.owner);
    }

    @Override
    public @NotNull Set<UUID> members() {
        return Optional.ofNullable(this.members).orElse(Set.of());
    }
    
}
