package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.AccountRepository;
import com.minhub.homebancking.Repository.ClientRepository;
import com.minhub.homebancking.Repository.TransactionRepository;
import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.Client;
import com.minhub.homebancking.models.Transaction;
import com.minhub.homebancking.models.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {
@Autowired
AccountRepository accountRepository;

@Autowired
ClientRepository clientRepository;

@Autowired
TransactionRepository transactionRepository;

@Transactional
@PostMapping("/transaction")
public ResponseEntity<Object> createTransaction (Authentication authentication,
                                                 @RequestParam Double amount,
                                                 @RequestParam String description,
                                                 @RequestParam String originAccount,
                                                 @RequestParam String destinationAccount,
                                                 @RequestParam Double restAccount){
    Account account1 = accountRepository.findByNumber(originAccount);{
    Account account2 = accountRepository.findByNumber(destinationAccount);
    Client client = clientRepository.findByEmail(authentication.getName());
    Set<Account> clientAccounts = client.getAccounts();









    if (amount == null || description.isEmpty() || originAccount.isEmpty()|| destinationAccount.isEmpty()){
        return new ResponseEntity<>("Completar todos los campos", HttpStatus.FORBIDDEN);
    }

    if (originAccount.equals(destinationAccount)){
        return new ResponseEntity<>("Disculpe, no se pueden realizar transacciones a la misma cuenta", HttpStatus.FORBIDDEN);
    }

    if ((account1 == null) || (account2 == null)){
        return new ResponseEntity<>("Seleccione las cuentas", HttpStatus.FORBIDDEN);
    }

    if( accountRepository.findByNumber(originAccount) ==null ){
        return new ResponseEntity<>("La cuenta ingresada no existe",HttpStatus.FORBIDDEN);

    }
    if (amount > account1.getBalance()){
        return new ResponseEntity<>("La cuenta no posee los fondos sufucicientes para realizar la transacciòn", HttpStatus.FORBIDDEN);
    }



    account1.setBalance(account1.getBalance()-amount);
    account2.setBalance(account2.getBalance()+amount);
    accountRepository.save(account1);
    accountRepository.save(account2);
    Transaction transaction1 = new Transaction(TransactionType.DEBT,Double.parseDouble("-"+ amount),description, LocalDateTime.now(),account1);
    Transaction transaction2 = new Transaction(TransactionType.CREDIT, amount,description, LocalDateTime.now(),account2);
    transactionRepository.save(transaction1);
    transactionRepository.save(transaction2);
    return new ResponseEntity<>("La transaccion ha sido realizada con exito", HttpStatus.CREATED);

    }






}}






