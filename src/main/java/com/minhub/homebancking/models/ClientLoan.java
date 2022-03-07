package com.minhub.homebancking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity //antacion para que se cree una tabla en la base de datos
public class ClientLoan { //en primer lugar se crean los atributos de la clase
    @Id // generador automticos de -id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;

    private double amount;
    private Integer payments;
    private LocalDate date; // ver de tarea 3


    @ManyToOne(fetch = FetchType.EAGER) //Relacion muuchos a uno (Fetch me trae los elementos//.EAGER me trae los elementos relacionados // Lazy el pedido es a requerimiento)
    @JoinColumn(name = "client_id") //Join Column es agregar una columna a la tabla ClientLoan; llamada "client_id"//
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)//Esto es una relacion de muchos a uno
    @JoinColumn(name = "loan_id")//en esta linea se agrega una columna a la tabla ClientLoan llamada "loan_id"//
    private Loan loan;


    public ClientLoan() { // por convencion se crea el primer constructor vacio//
    }

    public ClientLoan(double amount,Integer payments, Client client, Loan loan) {
        this.amount = amount;
        this.payments = payments;
        this.client = client;
        this.loan = loan;
    }

    public long getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}

