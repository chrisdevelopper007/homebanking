package com.minhub.homebancking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    private LocalDateTime creationDate;
    private double balance;


    @ManyToOne(fetch = FetchType.EAGER) //Lado de la Relacion muchos a uno
    @JoinColumn(name = "Client_id") // la columna Id de client se agrega a la tabla de accounts
    private Client client; // esta es la columna que relaciona el nombre de mappedBy del lado de la relacion 1 a muchos, que debe ser coincidente: al igual que el ejempo siguiente(@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Card> cards = new HashSet<>();



    private Boolean activeAccount;

    private AccountType type;

    public Account() {

    }


    public Account(String number,  LocalDateTime creationDate, double balance,Client client, Boolean activeAccount, AccountType type) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
        this.activeAccount= activeAccount;
        this.type= type;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = LocalDateTime.parse(creationDate);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

@JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void addTransaction (Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
            }
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Boolean getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", balance=" + balance +
            ", client=" + client +
                '}';
    }


}





