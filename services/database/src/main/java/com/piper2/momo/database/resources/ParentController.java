package com.piper2.momo.database.resources;

import com.piper2.momo.database.constacts.AccountType;
import com.piper2.momo.database.models.auxilially.Deposit;
import com.piper2.momo.database.models.auxilially.Response;
import com.piper2.momo.database.models.core.Account;
import com.piper2.momo.database.models.core.Student;
import com.piper2.momo.database.models.core.StudentParent;
import com.piper2.momo.database.repositories.AccountsRepository;
import com.piper2.momo.database.repositories.ParentRepository;
import com.piper2.momo.database.repositories.StudentRepository;
import com.piper2.momo.database.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/momo/parent")
public class ParentController {

    private final
    ParentRepository parentRepository;

    private final
    StudentRepository studentRepository;

    private final
    AccountsRepository accountsRepository;

    private final
    TransactionRepository transactionRepository;

    public ParentController(ParentRepository parentRepository, StudentRepository studentRepository, AccountsRepository accountsRepository, TransactionRepository transactionRepository) {
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;
        this.accountsRepository = accountsRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/add")
    public Response addParentToDatabase(@RequestBody final StudentParent parent){

        try {
            if (parentRepository.findByTelephone(parent.getTelephone()).isPresent()){
                throw new Exception("Phone number already registered");
            }
            StudentParent p = parentRepository.save(parent);
            Account acc = createParentAccount(p.getId());
            p.setAccountNumber(acc.getAccountNumber());

            return new Response("OK", parentRepository.save(p));
        }catch (Exception ex) {
            return new Response("Error: " + ex.getMessage(),null);
        }
    }


    private Account createParentAccount(int parent){
        return accountsRepository.save(new Account(AccountType.PARENT, parent));
    }

    @PostMapping("/student/add")
    public Response addStudentToParent(@RequestBody final Student student){
        try {
            if (!parentRepository.findByTelephone(student.getParent()).isPresent()){
                throw new Exception("Parent Id not found");
            }

//            TODO: check for duplicating students by name

            Account newStudentAccount = createStudentAccount(student.getId(),student.getParent());
            student.setAccountNumber(newStudentAccount.getAccountNumber());
            return new Response("OK", studentRepository.save(student));
        }catch (Exception ex) {
            return new Response("Error: " + ex.getMessage(),null);
        }
    }

    private Account createStudentAccount(int student, String parent){
        return accountsRepository.save(new Account(AccountType.STUDENT, student, parent));
    }

    @PatchMapping("/deposit")
    public Response depositToStudentAccount(@RequestBody final Deposit deposit){
        try {

            return new Response("OK",
                    new TransactionController(accountsRepository, transactionRepository)
                            .deposit(deposit.getAmount(),deposit.getFrom(), deposit.getTo()));
        }catch (Exception ex) {
            return new Response("Error: "+ ex.getMessage(), null);
        }
    }

    @GetMapping("/{telephone}")
    public Response getParent(@PathVariable ("telephone") final String telephone){
        return parentRepository.findByTelephone(telephone).isPresent() ?
                new Response("OK", parentRepository.findByTelephone(telephone).get()) :
                new Response("Error: User was not found." , null);
    }

    @GetMapping("/{telephone}/children")
    public Response getChildren(@PathVariable final String telephone){
            return new Response("OK", studentRepository.findAllByParent(telephone));
    }

    @GetMapping("/{telephone}/transactions")
    public Response getAllTransaction(@PathVariable String telephone){
        try {
            if (!parentRepository.findByTelephone(telephone).isPresent()){
                throw new Exception("Error: User was not found");
            }

            StudentParent sp = parentRepository.findByTelephone(telephone).get();

            return new Response("OK", transactionRepository.findAllByFromAccountOrToAccount(sp.getAccountNumber(),sp.getAccountNumber()));

        } catch (Exception ex) {
            return new Response(ex.getMessage(), null);
        }
    }
}
