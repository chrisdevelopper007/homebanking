package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.AccountRepository;
import com.minhub.homebancking.Repository.ClientRepository;
import com.minhub.homebancking.dtos.ClientDto;
import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.AccountType;
import com.minhub.homebancking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class ClientControllers {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping("/clients")
    public List<ClientDto> getClients() {
        List<ClientDto> clients = clientRepository.findAll().stream().map(client -> new ClientDto(client)).collect(Collectors.toList());
        return clients;
    }

    @RequestMapping("clients/{id}")
    public ClientDto getClient(@PathVariable Long id) {
        ClientDto clientDto = new ClientDto(clientRepository.findById(id).orElse(null));
        return clientDto;
    }

    @PostMapping("/clients")

    public ResponseEntity<Object> register( // un metodo publico que retorna un responseEntity (respuesta)llamado register

                                            @RequestParam String firstName, @RequestParam String lastName, //parametros  requerido por los cuales voy a emplear

                                            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            //.isEmpty() es un metodo que responde entre parentesis lo que devuelve(retorna un boolean Vo F)// || or es un condicional//
            return new ResponseEntity<>("Por favor, complete todos los campos", HttpStatus.FORBIDDEN);
            // retorna una new respuestaEntity(instancia)cuando este mal(en la consola va a responder un string en el body, y un HttpStatus.FORBIDDEN)

        }


        if (clientRepository.findByEmail(email) != null) {
            // el segundo condicional busca por email en el repositorio de client // .findByEmail este metodo busca dentro el client reposito
            // (email) es el parametro que se coloca dentro del metodo y se lo saca directamente de los @RequestParam que tenemso declarados

            return new ResponseEntity<>("Disculpe, ese correo se encuentra en uso", HttpStatus.FORBIDDEN);
            // retorna una instancia de la entidad entidad de respuesta con los siguientes parametros(Body:strin,HttpStatus.FORBIDDEN: osea error)

        }
        Random numberRandom = new Random(); // crea un numero random del 0 al 1000
        int accountRandom = numberRandom.nextInt(1000);



        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientRepository.save(client);
        // si no ocurre ningun condicional el metodo .save guardaria un nuevo cliente

        Account account = new Account("VIN000"+accountRandom, LocalDateTime.now(), 0.0,client,true, AccountType.CAJA_DE_AHORRO_EN_PESOS);
        accountRepository.save(account);
        return new ResponseEntity<>("Se ha creado su cuenta con exito", HttpStatus.CREATED);

       


    }
    @RequestMapping("/clients/current")
    public ClientDto getAll(Authentication authentication) {
        ClientDto clientEmail = new ClientDto(clientRepository.findByEmail(authentication.getName()));
        return clientEmail;
    }
}
