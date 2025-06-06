package ovh.mythmc.union.economy.v1.account.filter;

import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.BankAccount;

final class BankAccountFilterImpl extends AbstractAccountFilter<BankAccountFilter, BankAccount, String> implements BankAccountFilter {

    private final Set<BankAccount> accounts;

    BankAccountFilterImpl(@NotNull Set<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    Set<BankAccount> accounts() {
        return this.accounts;
    }

    @Override
    public @NotNull BankAccountFilter owner(@NotNull UUID owner) {
        accounts.removeIf(account -> !account.isOwner(owner));
        return this;
    }

    @Override
    public @NotNull BankAccountFilter member(@NotNull UUID member) {
        accounts.removeIf(account -> !account.isMember(member));
        return this;
    }
    
}
