package com.minhub.homebancking.controllers;

import com.minhub.homebancking.Repository.AccountRepository;
import com.minhub.homebancking.Repository.CardRepository;
import com.minhub.homebancking.dtos.PagoAplicationDTO;
import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class PagoController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @PostMapping("/pago")
    public ResponseEntity<Object> crearPago (@RequestBody PagoAplicationDTO pagoAplicationDTO
                                             ){
        Card card1 = cardRepository.findByNumber(pagoAplicationDTO.getNumeroDeTarjeta());
        Account account1 = accountRepository.findByNumber(pagoAplicationDTO.getCuentaADebitar());
        if(!Objects.equals(card1.getNumber(), pagoAplicationDTO.getNumeroDeTarjeta())){
            return new ResponseEntity<>("invalid data" , HttpStatus.FORBIDDEN);
        }

        if (card1.getThruDate().isAfter(LocalDate.now())){
            return new ResponseEntity<>("la tarjeta se encuentra vencida", HttpStatus.FORBIDDEN);
        }





        return null;
    }

}
