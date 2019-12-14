package com.piper2.momo.database.resources;

import com.piper2.momo.database.models.auxilially.Deposit;
import com.piper2.momo.database.models.core.Account;
import com.piper2.momo.database.models.core.Transaction;
import com.piper2.momo.database.repositories.AccountsRepository;
import com.piper2.momo.database.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

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

        Account accountReceiving, accountGiving;

        if (!accountsRepository.findByAccountNumber(to).isPresent()){
            throw new Exception("Receiving account does not exist");
        }

        if (!accountsRepository.findByAccountNumber(from).isPresent()){
            throw new Exception("Sending account does not exist");
        }

        accountGiving = accountsRepository.findByAccountNumber(from).get();
        accountReceiving = accountsRepository.findByAccountNumber(to).get();

        withdraw(accountGiving.getAccountNumber(),amount);

        accountReceiving.setBalance(accountReceiving.getBalance() + amount);
//        accountGiving.setBalance(accountGiving.getBalance() - amount);

        accountsRepository.save(accountReceiving);
        transactionRepository.save(new Transaction(from,to,amount));

        return new Deposit(amount, to, accountGiving.getBalance());
    }

    private boolean withdraw(long from, float amount) throws Exception{
        if (!accountsRepository.findByAccountNumber(from).isPresent()){
            throw new Exception("Withdraw account is unknown");
        }
        Account account = accountsRepository.findByAccountNumber(from).get();

        if (account.getBalance() < amount){
            throw new Exception("Insufficient balance");
        } else {
            account.setBalance(account.getBalance() - amount);
            accountsRepository.save(account);
            transactionRepository.save(new Transaction(from,0,amount));
            return true;
        }
    }
}
