package ovh.mythmc.union.economy.v1.account.option;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.option.AccountOptions.AccountOptionsBuilder;
import ovh.mythmc.union.economy.v1.account.option.BankAccountOptions.BankAccountOptionsBuilder;

final class BankAccountOptionsBuilderImpl implements BankAccountOptionsBuilder {

    private String worldName;

    private UUID owner;

    private final Set<UUID> members = new HashSet<>();

    @Override
    public @NotNull AccountOptionsBuilder<BankAccountOptions> worldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    @Override
    public @NotNull BankAccountOptionsBuilder owner(UUID owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public @NotNull BankAccountOptionsBuilder members(@NotNull Collection<UUID> members) {
        this.members.addAll(members);
        return this;
    }

    @Override
    public @NotNull BankAccountOptions build() {
        return new BankAccountOptionsImpl(worldName, owner, members);
    }
    
}
