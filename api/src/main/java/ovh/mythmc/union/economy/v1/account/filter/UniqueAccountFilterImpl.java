package ovh.mythmc.union.economy.v1.account.filter;

import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.UniqueAccount;

final class UniqueAccountFilterImpl extends AbstractAccountFilter<UniqueAccountFilterImpl, UniqueAccount, UUID> {

    private final Set<UniqueAccount> uniqueAccounts;

    UniqueAccountFilterImpl(@NotNull Set<UniqueAccount> uniqueAccounts) {
        this.uniqueAccounts = uniqueAccounts;
    }

    @Override
    Set<UniqueAccount> accounts() {
        return this.uniqueAccounts;
    }
    
}
