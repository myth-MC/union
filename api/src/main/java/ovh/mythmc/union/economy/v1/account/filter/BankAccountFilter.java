package ovh.mythmc.union.economy.v1.account.filter;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.BankAccount;

public interface BankAccountFilter extends AccountFilter<BankAccountFilter, BankAccount, String> {

    @NotNull BankAccountFilter owner(@NotNull UUID owner);

    @NotNull BankAccountFilter member(@NotNull UUID member);
    
}
