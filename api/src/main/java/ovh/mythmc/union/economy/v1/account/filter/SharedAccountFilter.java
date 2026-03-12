package ovh.mythmc.union.economy.v1.account.filter;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.SharedAccount;

public interface SharedAccountFilter extends AccountFilter<SharedAccountFilter, SharedAccount, String> {

    @NotNull SharedAccountFilter owner(@NotNull UUID owner);

    @NotNull SharedAccountFilter member(@NotNull UUID member);
    
}
