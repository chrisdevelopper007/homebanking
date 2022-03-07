package com.minhub.homebancking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity //anotacion para que se cree una tabla en la base de datos
public class Card {


    // 1ro Creo las propiedades de la clase
    @Id // generador automticos de -id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
//
    String cardholder;
    CardType type;
    CardColor color;
    String number;
    int cvv;
    LocalDate thruDate;
    LocalDate fromDate;

    @ManyToOne(fetch = FetchType.EAGER) //Lado de la Relacion muchos a uno
    @JoinColumn(name = "Client_id") // la columna Id de client se agrega a la tabla de accounts
    private Client client; // esta es la columna que relaciona el nombre de mappedBy del lado de la relacion 1 a muchos, que debe ser coincidente: al igual que el ejempo siguiente(@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Account_id")
    private Account account;

    // 2do Creo los constructores
    public Card() { // Primero el constructor vacio por convencion)
    }
                    // Segundo el constructor con los atributos
    public Card(String cardholder, CardType type, CardColor color, String number, int cvv, LocalDate thruDate, LocalDate fromDate, Client client, Account account) {
        this.cardholder = cardholder;
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.client = client;
        this.account= account;
    }
// 3ro creo los metodos accesores
    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

@JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardholder='" + cardholder + '\'' +
                ", type=" + type +
                ", color=" + color +
                ", number=" + number +
                ", cvv=" + cvv +
                ", thruDate=" + thruDate +
                ", fromDate=" + fromDate +
                ", client=" + client +
                '}';
    }
}
