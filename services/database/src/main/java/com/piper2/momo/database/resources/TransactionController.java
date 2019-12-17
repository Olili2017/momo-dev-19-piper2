package com.piper2.momo.database.resources;

import com.piper2.momo.database.constacts.Transactions;
import com.piper2.momo.database.models.auxilially.Deposit;
import com.piper2.momo.database.models.auxilially.Response;
import com.piper2.momo.database.models.auxilially.Withdraw;
import com.piper2.momo.database.models.core.Account;
import com.piper2.momo.database.models.core.Transaction;
import com.piper2.momo.database.repositories.AccountsRepository;
import com.piper2.momo.database.repositories.TransactionRepository;

public class TransactionController {

    private AccountsRepository accountsRepository;
    private TransactionRepository transactionRepository;

    public TransactionController(){

    }

    TransactionController(AccountsRepository accountsRepository, TransactionRepository transactionRepository) {
        this.accountsRepository = accountsRepository;
        this.transactionRepository = transactionRepository;
    }

    Deposit deposit(float amount, long from, long to) throws Exception {

        if (amount < Transactions.DEPOSIT_THRESHOLD){
            throw new Exception("Deposit amount must be more than " + Transactions.DEPOSIT_THRESHOLD);
        }

        Account accountReceiving, accountGiving;

        if (!accountsRepository.findByAccountNumber(to).isPresent()){
            throw new Exception("Receiving account does not exist");
        }

        if (!accountsRepository.findByAccountNumber(from).isPresent()){
            throw new Exception("Sending account does not exist");
        }

        accountGiving = accountsRepository.findByAccountNumber(from).get();
        accountReceiving = accountsRepository.findByAccountNumber(to).get();

        transferMoney(amount, accountGiving.getAccountNumber(), accountReceiving.getAccountNumber());

        accountReceiving.setBalance(accountReceiving.getBalance() + amount);

        accountsRepository.save(accountReceiving);
        transactionRepository.save(new Transaction(from,to,amount));

        return new Deposit(amount, to, accountGiving.getBalance());
    }

    Response depositToParent(float amount, long parentAccount){

        if(accountsRepository.findByAccountNumber(parentAccount).isPresent()){
            Account pAcc = accountsRepository.findByAccountNumber(parentAccount).get();
            pAcc.setBalance(pAcc.getBalance() + amount);
            return new Response("Success!", new Deposit(amount, parentAccount, accountsRepository.save(pAcc).getBalance()));
        }
        return new Response("Unknown account", null);
    }

    private void transferMoney(float amount, long from, long to) throws Exception {
        if (amount < Transactions.TRANSFER_THRESHOLD){
            throw new Exception("Transfer amount must be more than " + Transactions.TRANSFER_THRESHOLD);
        }

        if (!accountsRepository.findByAccountNumber(from).isPresent()){
            throw new Exception("Withdraw account is unknown");
        }
        Account givingAccount = accountsRepository.findByAccountNumber(from).get();
//        Account recievingAccount = accountsRepository.findByAccountNumber(to).get();

        if (givingAccount.getBalance() < amount){
            throw new Exception("Insufficient balance");
        } else {
            givingAccount.setBalance(givingAccount.getBalance() - amount);
            accountsRepository.save(givingAccount);
            transactionRepository.save(new Transaction(from,to,amount));
        }
    }

    Withdraw withdraw(long from, float amount) throws Exception{
        if (amount < Transactions.WITHDRAW_THRESHOLD){
            throw new Exception("Withdraw amount must be more than " + Transactions.WITHDRAW_THRESHOLD);
        }

        if (!accountsRepository.findByAccountNumber(from).isPresent()){
            throw new Exception("Withdraw account is unknown");
        }
        Account account = accountsRepository.findByAccountNumber(from).get();

        if (account.getBalance() < amount){
            throw new Exception("Insufficient balance");
        } else {
            account.setBalance(account.getBalance() - amount);
            Account newAcc = accountsRepository.save(account);
            transactionRepository.save(new Transaction(from,0,amount));

            return new Withdraw(from, amount, newAcc.getBalance());
        }
    }
}
