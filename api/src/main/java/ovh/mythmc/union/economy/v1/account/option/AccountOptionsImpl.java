package ovh.mythmc.union.economy.v1.account.option;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

non-sealed class AccountOptionsImpl implements AccountOptions {

    private final String worldName;

    AccountOptionsImpl(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public @NotNull Optional<String> worldName() {
        return Optional.ofNullable(this.worldName);
    }
    
}
