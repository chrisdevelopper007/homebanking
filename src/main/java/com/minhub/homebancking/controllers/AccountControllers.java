package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.AccountRepository;
import com.minhub.homebancking.Repository.ClientRepository;
import com.minhub.homebancking.dtos.AccountDto;
import com.minhub.homebancking.dtos.ClientDto;
import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.AccountType;
import com.minhub.homebancking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api")
public class AccountControllers {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<AccountDto> getAccounts(){
        List<AccountDto>accounts = accountRepository.findAll().stream().map(account -> new AccountDto(account)).collect(Collectors.toList());
        return accounts;
    }

    @RequestMapping("accounts/{id}")
    public AccountDto getAccount(@PathVariable Long id){
        AccountDto accountDto = new AccountDto(accountRepository.findById(id).orElse(null));
        return accountDto;
    }

//    Client client = clientRepository.findByEmail(authentication);

    @PostMapping("clients/current/accounts")
    public ResponseEntity<Object> createAccounts(Authentication authentication, @RequestParam AccountType accountType) {
        Client clientEmail = clientRepository.findByEmail(authentication.getName());
        if (clientEmail.getAccounts().stream().filter(cuenta->cuenta.getActiveAccount()==true).collect(Collectors.toList()).size() > 3) {
            return new ResponseEntity<>("Disculpe,usted ya posee 3 cuentas", HttpStatus.FORBIDDEN);
        }
        Random numberRandom = new Random(); // crea un numero random del 0 al 1000

        int accountRandom = numberRandom.nextInt(99999999);
        Account account = new Account("VIN-"+accountRandom, LocalDateTime.now(), 0.0,clientEmail,true, accountType);
        accountRepository.save(account);
        return new ResponseEntity<>("Se ha creado su cuenta con exito", HttpStatus.CREATED);

    }

    @PatchMapping("clients/current/accounts/{id}")
    public ResponseEntity<Object> deleteAccount (@PathVariable Long id, @RequestParam Boolean activeAccount) {


        Account accountSave = accountRepository.findById(id).orElse(null);

        accountSave.setActiveAccount(activeAccount);

        accountRepository.save(accountSave);


        return new ResponseEntity<>("Se ha eliminado la cuenta",HttpStatus.OK);
    }




}
