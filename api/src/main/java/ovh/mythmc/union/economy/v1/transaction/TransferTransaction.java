package ovh.mythmc.union.economy.v1.transaction;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.economy.v1.account.Account;

public sealed interface TransferTransaction extends Transaction permits TransferTransactionImpl {

    @NotNull Account<?> toAccount();
    
}
