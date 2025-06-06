package ovh.mythmc.union.economy.v1.account.filter;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.VirtualAccount;

final class VirtualAccountFilterImpl extends AbstractAccountFilter<VirtualAccountFilterImpl, VirtualAccount, String> {

    private final Set<VirtualAccount> accounts;

    VirtualAccountFilterImpl(@NotNull Set<VirtualAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    Set<VirtualAccount> accounts() {
        return this.accounts;
    }
    
}
