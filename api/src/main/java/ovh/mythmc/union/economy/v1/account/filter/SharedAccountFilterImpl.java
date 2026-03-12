package ovh.mythmc.union.economy.v1.account.filter;

import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.SharedAccount;

final class SharedAccountFilterImpl extends AbstractAccountFilter<SharedAccountFilter, SharedAccount, String> implements SharedAccountFilter {

    private final Set<SharedAccount> accounts;

    SharedAccountFilterImpl(@NotNull Set<SharedAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    Set<SharedAccount> accounts() {
        return this.accounts;
    }

    @Override
    public @NotNull SharedAccountFilter owner(@NotNull UUID owner) {
        accounts.removeIf(account -> !account.isOwner(owner));
        return this;
    }

    @Override
    public @NotNull SharedAccountFilter member(@NotNull UUID member) {
        accounts.removeIf(account -> !account.isMember(member));
        return this;
    }
    
}
