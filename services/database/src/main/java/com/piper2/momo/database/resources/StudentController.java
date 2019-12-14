package com.piper2.momo.database.resources;

import com.piper2.momo.database.models.auxilially.Response;
import com.piper2.momo.database.models.auxilially.Withdraw;
import com.piper2.momo.database.repositories.AccountsRepository;
import com.piper2.momo.database.repositories.StudentRepository;
import com.piper2.momo.database.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/momo/student")
public class StudentController {

    private AccountsRepository accountsRepository;
    private TransactionRepository transactionRepository;
    private StudentRepository studentRepository;

    public StudentController(AccountsRepository accountsRepository, TransactionRepository transactionRepository, StudentRepository studentRepository) {
        this.accountsRepository = accountsRepository;
        this.transactionRepository = transactionRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{account}")
    public Response getStudent(@PathVariable long account){
        try {
            if (!studentRepository.findByAccountNumber(account).isPresent()){
                throw new Exception("Unknown account");
            }

            return new Response("OK", studentRepository.findByAccountNumber(account).get());

        } catch (Exception ex) {
            return new Response("Error: "+ ex.getMessage(), null);
        }
    }

    @GetMapping("/balance/{account}")
    public Response getMyBalance(@PathVariable long account){
        try {
            if (!accountsRepository.findByAccountNumber(account).isPresent()){
                throw new Exception("Unknown account");
            }

            return new Response("OK", accountsRepository.findByAccountNumber(account).get().getBalance());

        } catch (Exception ex) {
            return new Response("Error: "+ ex.getMessage(), null);
        }
    }

    @PatchMapping("/withdraw")
    public Response withdrawMoney(@RequestBody final Withdraw withdraw){
        try {
            if (!accountsRepository.findByAccountNumber(withdraw.getFrom()).isPresent()){
                throw new Exception("Account not known");
            }

            return new Response("OK", new TransactionController(accountsRepository,transactionRepository).withdraw(withdraw.getFrom(),withdraw.getAmount()));
        } catch (Exception ex) {
            return new Response("Error: "+ ex.getMessage(), null);
        }
    }


    @GetMapping("/{account}/transactions")
    public Response getAllTransaction(@PathVariable long account){
        try {
            if (!accountsRepository.findByAccountNumber(account).isPresent()){
                throw new Exception("Error: Account was not found");
            }

            return new Response("OK", transactionRepository.findAllByFromAccountOrToAccount(account, account));

        } catch (Exception ex) {
            return new Response(ex.getMessage(), null);
        }
    }

//    TODO: request money

//    TODO: change pin

}
