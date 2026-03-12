package ovh.mythmc.union.economy.v1.account.option;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.option.AccountOptions.AccountOptionsBuilder;
import ovh.mythmc.union.economy.v1.account.option.SharedAccountOptions.SharedAccountOptionsBuilder;

final class SharedAccountOptionsBuilderImpl implements SharedAccountOptionsBuilder {

    private String worldName;

    private UUID owner;

    private final Set<UUID> members = new HashSet<>();

    @Override
    public @NotNull AccountOptionsBuilder<SharedAccountOptions> worldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    @Override
    public @NotNull SharedAccountOptionsBuilder owner(UUID owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public @NotNull SharedAccountOptionsBuilder members(@NotNull Collection<UUID> members) {
        this.members.addAll(members);
        return this;
    }

    @Override
    public @NotNull SharedAccountOptions build() {
        return new SharedAccountOptionsImpl(worldName, owner, members);
    }
    
}
