package ovh.mythmc.union.economy.v1.account.option;

import org.jetbrains.annotations.NotNull;

final class AccountOptionsBuilderImpl implements AccountOptions.AccountOptionsBuilder<AccountOptions> {

    private String worldName;

    @Override
    public @NotNull AccountOptions.AccountOptionsBuilder<AccountOptions> worldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    @Override
    public @NotNull AccountOptions build() {
        return new AccountOptionsImpl(
            this.worldName
        );
    }
    
}
