package ovh.mythmc.union.economy.v1.account.option;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.option.SharedAccountOptions.SharedAccountOptionsBuilder;

public sealed interface AccountOptions permits AccountOptionsImpl, SharedAccountOptions {

    static AccountOptions empty() {
        return builder().build();
    }

    static AccountOptionsBuilder<AccountOptions> builder() {
        return new AccountOptionsBuilderImpl();
    }

    @NotNull Optional<String> worldName();

    sealed interface AccountOptionsBuilder<T extends AccountOptions> permits AccountOptionsBuilderImpl, SharedAccountOptionsBuilder {

        @NotNull AccountOptionsBuilder<T> worldName(String worldName);
    
        @NotNull T build();
        
    }
    
}
