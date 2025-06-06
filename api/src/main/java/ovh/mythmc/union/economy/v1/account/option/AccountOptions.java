package ovh.mythmc.union.economy.v1.account.option;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.option.BankAccountOptions.BankAccountOptionsBuilder;

public sealed interface AccountOptions permits AccountOptionsImpl, BankAccountOptions {

    static AccountOptions empty() {
        return builder().build();
    }

    static AccountOptionsBuilder<AccountOptions> builder() {
        return new AccountOptionsBuilderImpl();
    }

    @NotNull Optional<String> worldName();

    sealed interface AccountOptionsBuilder<T extends AccountOptions> permits AccountOptionsBuilderImpl, BankAccountOptionsBuilder {

        @NotNull AccountOptionsBuilder<T> worldName(String worldName);
    
        @NotNull T build();
        
    }
    
}
