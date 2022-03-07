
package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.*;
import com.minhub.homebancking.dtos.ClientDto;
import com.minhub.homebancking.dtos.LoanAplicationDTO;
import com.minhub.homebancking.dtos.LoanDTO;
import com.minhub.homebancking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController // le dice a Spring que se va a crear un servicio o ruta que me permita interactuar  entre el back y el front ////Anotación que se encarga de relacionar un método con una petición http.
@RequestMapping("/api") // Peticiòn tipo get a la API // estamos diciendo que se acceda a servicios correspondientes a esa APi desarrollada de la misma manera(en forma consistente)
public class LoanController {
//debo crear los metodos dentro de la clase para enviar y recibir información

    // ("/api/login") es la ruta de acceso URI = path + query strinf
    // http:localhost/api/login  URL
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Transactional
    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }


    @PostMapping("/loans")
    public ResponseEntity<Object> createLoan (Authentication authentication,
                                              @RequestBody LoanAplicationDTO loanAplicationDTO){

        Client client = clientRepository.findByEmail(authentication.getName());
        Account account = accountRepository.findByNumber(loanAplicationDTO.getDestinyAccount());
        Loan loan = loanRepository.findByName(loanAplicationDTO.getName());



        if (loanAplicationDTO.getAmount().toString().isEmpty()|| loanAplicationDTO.getPayments().toString().isEmpty()||loanAplicationDTO.getDestinyAccount().isEmpty()){
            if (loanAplicationDTO.getAmount()==0||loanAplicationDTO.getPayment()==0)
            return new ResponseEntity<>("invalid data" , HttpStatus.FORBIDDEN);//verificaciòn que los datos no esten vacios
        }

        if(loanRepository.findByName(loan.getName())==null){//verificaciòn que el prestamo exista
            return new ResponseEntity<>("the loan dosn`t exist",HttpStatus.FORBIDDEN);
        }

        if(loanAplicationDTO.getAmount()>loan.getMaxAmount()){
            return new ResponseEntity<>("invalid amount",HttpStatus.FORBIDDEN);
        }

        if(loan.getPayments().stream().filter(payment -> payment.equals(loanAplicationDTO.getPayments())).collect(Collectors.toList()).toString()==null){
            return new ResponseEntity<>("enter the correct payments",HttpStatus.FORBIDDEN);
        }
        if( !account.getNumber().equals(loanAplicationDTO.getDestinyAccount())) { //verificar que la cuenta de destino exista comparando las cuentas con la cuenta de destino del body recibido
            return new ResponseEntity<>("The account of destiny doesnt exist", HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(account)){ // verificar que la cuenta de  destino pertenezca al cliente autenticado
            return new ResponseEntity<>("the destination account does not belong to you", HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan1 = new ClientLoan(loanAplicationDTO.getAmount()+(loanAplicationDTO.getAmount()*0.2), loanAplicationDTO.getPayment(),client,loan);
        clientLoanRepository.save(clientLoan1);


        Transaction transaction1 = new Transaction(TransactionType.CREDIT, loanAplicationDTO.getAmount(), loan.getName() + "loan approved", LocalDateTime.now(),account);
        transactionRepository.save(transaction1);

        account.setBalance(account.getBalance()+loanAplicationDTO.getAmount());
        accountRepository.save(account);





        return new ResponseEntity<>("Loan was created",HttpStatus.CREATED);


    }


   @PostMapping("/loans/admin")
    public ResponseEntity<Object> crearPrestamoAdmin(Authentication authentication, @RequestBody LoanAplicationDTO loanAplicationDTO) {
       if (loanAplicationDTO.getName().isEmpty() || loanAplicationDTO.getMaxAmount()==0 || loanAplicationDTO.getPayments() == null ){
           return new ResponseEntity<>("Complete todos los campos",HttpStatus.FORBIDDEN);
       }


       Loan loanAdmin = new Loan(loanAplicationDTO.getName(), loanAplicationDTO.getMaxAmount(), loanAplicationDTO.getPayments());
       loanRepository.save(loanAdmin);



        return new ResponseEntity<>("El prestamo ha sido creado",HttpStatus.CREATED);
    }
}

