package com.minhub.homebancking;

import com.minhub.homebancking.Repository.*;
import com.minhub.homebancking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.minhub.homebancking.models.TransactionType.CREDIT;

@SpringBootApplication
public class HomebanckingApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(HomebanckingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientrepository,
                                      AccountRepository accountrepository,
                                      TransactionRepository transactionRepository,
                                      LoanRepository loanRepository,
                                      ClientLoanRepository clientLoanRepository,
                                      CardRepository cardRepository) {
        return (args) -> {

            Client melba = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("20200300"));
            Client norma = new Client("Norma","Leguizamon","norma@mindhub.com", passwordEncoder.encode("10200200"));
            Client chris = new Client("Christian", "Diaz Leguizamon", "christian@mindhub.com", passwordEncoder.encode("1234"));

            clientrepository.save(melba);
            clientrepository.save(norma);
            clientrepository.save(chris);




           Account vin001 = new Account("vin001",LocalDateTime.now(), 5000.00,melba,true,AccountType.CAJA_DE_AHORRO_EN_PESOS);
           Account vin002 = new Account("vin002", LocalDateTime.now(), 5000.00, clientrepository.save(norma),true,AccountType.CAJA_DE_AHORRO_EN_DOLARES);
            Account vin003 = new Account("vin003",LocalDateTime.of(2022,1,18,15,31), 7500.00,melba,true,AccountType.CUENTA_CORRIENTE_EN_PESOS);
            Account vin004 = new Account("vin004",LocalDateTime.of(2022,1,18,15, 31), 7500.00,norma,true,AccountType.CUENTA_CORRIENTE_EN_PESOS);
           accountrepository.save(vin001);
           accountrepository.save(vin002);
            accountrepository.save(vin003);
            accountrepository.save(vin004);

            Transaction melba001 = new Transaction(CREDIT,150000.00,"Haberes", LocalDateTime.now(),vin001);
            Transaction melba002 = new Transaction(CREDIT,10200.00,"Venta moneda ext",LocalDateTime.of(2022,01,21,15,30),vin002);
            Transaction melba003 = new Transaction(TransactionType.DEBT,-2000.00, "Claro - Telefonia celular", LocalDateTime.now(),vin001);
            Transaction melba004 = new Transaction(TransactionType.DEBT,-2500.00, "Movistar - Internet", LocalDateTime.now(),vin002);
            transactionRepository.save(melba001);
            transactionRepository.save(melba002);
            transactionRepository.save(melba003);
            transactionRepository.save(melba004);

            Transaction mica001 = new Transaction(CREDIT,200000.00,"Haberes",LocalDateTime.now(),vin003);
            Transaction mica002 = new Transaction(CREDIT,10200.00,"Venta moneda ext",LocalDateTime.of(2022,01,21,15,30),vin004);
            Transaction mica003 = new Transaction(TransactionType.DEBT,2000.00, "Tuenti - Telefonia celular", LocalDateTime.now(),vin003);
            Transaction mica004 = new Transaction(TransactionType.DEBT,2500.00, "Movistar - Internet", LocalDateTime.now(),vin004);

            transactionRepository.save(mica001);
            transactionRepository.save(mica002);
            transactionRepository.save(mica003);
            transactionRepository.save(mica004);

            List<Integer> paymentsHipotecario = Arrays.asList(12,24,36,48,60);
            List<Integer> paymentsPersonal = Arrays.asList(6,12,24);
            List<Integer> paymentsAutomotriz = Arrays.asList(6,12,24,36);

            Loan hipotecario = new Loan("Hipotecario",500000.00,paymentsHipotecario);
            Loan personal = new Loan("Personal",100000.00,paymentsPersonal);
            Loan automotriz = new Loan("Automotriz",300000.00,paymentsAutomotriz);



            loanRepository.save(hipotecario);
            loanRepository.save(personal);
            loanRepository.save(automotriz);

          ClientLoan clientLoan1 = new ClientLoan(5000.00,12,melba,hipotecario);
           clientLoanRepository.save(clientLoan1);

            System.out.println(clientLoan1);
            System.out.println(hipotecario);




            Card melbaGold = new Card("Melba Morel",CardType.DEBT,CardColor.GOLD,"90",724,LocalDate.of(2027, 12, 1),LocalDate.of(2022, 1, 10),melba,vin001);
            Card melbaTitanium = new Card("Melba Morel", CardType.CREDIT, CardColor.TITANIUM, "80", 912,LocalDate.of(2027,12,1),LocalDate.of(2022,1,10),melba,vin003);

        Card melbaSilver = new Card("Melba Morel",CardType.CREDIT,CardColor.SILVER,"98",567,LocalDate.now(),LocalDate.of(2025, 10, 12),melba, vin001);

            Card normaSilver = new Card("Norma Leguizamon", CardType.CREDIT,CardColor.SILVER,"99",983,LocalDate.of(2027, 01, 31),LocalDate.now(),norma, vin002);
            cardRepository.save(melbaGold);
            cardRepository.save(melbaTitanium);
            cardRepository.save(normaSilver);
            cardRepository.save(melbaSilver);

            System.out.println(vin001);
            System.out.println(vin002);
            System.out.println(vin003);
            System.out.println(vin004);
            System.out.println(melba001);

            System.out.println(melba001);







        };
    }

}
