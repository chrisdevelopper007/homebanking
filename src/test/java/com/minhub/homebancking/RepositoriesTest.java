package com.minhub.homebancking;

import com.minhub.homebancking.Repository.*;
import com.minhub.homebancking.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

//@DataJpaTest // para hacer los test dentro del JPA
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository transactionRepository;









    @Test

    public void existLoans(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans,is(not(empty())));

    }



    @Test

    public void existNameLoan(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
        assertThat(loans, hasItem(hasProperty("name", is("Hipotecario"))));
        assertThat(loans, hasItem(hasProperty("name", is("Automotriz"))));

    }

    @Test

    public void existAccounts(){

        List<Account> accounts = accountRepository.findAll();

        assertThat(accounts,is(not(empty())));

    }

    @Test

    public void existNumberAccounts(){

        List<Account> accounts = accountRepository.findAll();

        assertThat(accounts, hasItem(hasProperty("number", is("vin001"))));
        assertThat(accounts, hasItem(hasProperty("number", is("vin002"))));
        assertThat(accounts, hasItem(hasProperty("number", is("vin003"))));
        assertThat(accounts, hasItem(hasProperty("number", is("vin004"))));

    }

    @Test

    public void existCards(){

        List<Card> cards = cardRepository.findAll();

        assertThat(cards,is(not(empty())));

    }

    @Test

    public void existTypeCards(){

        List<Card> cards = cardRepository.findAll();

        assertThat(cards, hasItem(hasProperty("type", is(CardType.DEBT))));
        assertThat(cards, hasItem(hasProperty("type", is(CardType.CREDIT))));


    }

    @Test

    public void existClients(){

        List<Client> clients = clientRepository.findAll();

        assertThat(clients,is(not(empty())));

    }

    @Test

    public void existFirstNameClients(){

        List<Client> clients = clientRepository.findAll();

        assertThat(clients, hasItem(hasProperty("firstName", is("Melba"))));
        assertThat(clients, hasItem(hasProperty("firstName", is("Norma"))));


    }

    @Test

    public void existTransactions(){

        List<Transaction> transactions = transactionRepository.findAll();

        assertThat(transactions,is(not(empty())));

    }

    @Test

    public void existDescriptionTransaction(){

        List<Transaction> transactions = transactionRepository.findAll();

        assertThat(transactions, hasItem(hasProperty("description", is("Haberes"))));
        assertThat(transactions, hasItem(hasProperty("description", is("Venta moneda ext"))));


    }


    //realizar los @test para los repo de client, and transaction






}
