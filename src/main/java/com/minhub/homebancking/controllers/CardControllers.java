package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.CardRepository;
import com.minhub.homebancking.Repository.ClientRepository;
import com.minhub.homebancking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Random;

import static com.minhub.homebancking.utils.CardUtils.getCardNumber;
import static com.minhub.homebancking.utils.CardUtils.getCvv;

@RestController
@RequestMapping("/api")


public class CardControllers {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> registerCards(Authentication authentication,
                                                @RequestParam CardType cardType,
                                                @RequestParam CardColor cardColor,
                                                @RequestParam Account account
                                                ){
        Client client = clientRepository.findByEmail(authentication.getName());
        if (client.getCard().size() >5){
            return new ResponseEntity<>("Disculpe, usted ya posee demasiadas tarjetas", HttpStatus.FORBIDDEN);
        }
        else{
            Random numberRandom = new Random(); // crea un numero random del 0 al 1000

            int cvv = getCvv();

            String cardNumber = getCardNumber();
            


            Card card = new Card(client.getFirstName()+client.getLastName(),cardType,cardColor, cardNumber, cvv, LocalDate.now().plusYears(5), LocalDate.now(),client,account);
            cardRepository.save(card);
            return new ResponseEntity<>("La tarjeta ha sido creada", HttpStatus.CREATED);
        }
    }



}
