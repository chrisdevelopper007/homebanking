package com.minhub.homebancking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity //para que se cree una tabla en la base de datos//
public class Loan { // dentro declaro atributos y metodos//
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id; //sera la clave primaria //

    private String name; //privadotexto name //
    private double maxAmount; //propiedad monto maximo//


    @ElementCollection
    @Column(name="payments")
    private List<Integer>payments = new ArrayList<>();

    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    Set<ClientLoan> clientLoan = new HashSet<>(); //newHashSet para crear el espacio
// en segundo lugar se crean los constructores

    //el primer constructor se debe generar vacio por convencion
    public Loan() {
    }
    // el segundo constructor se debe generar con todos los atributos excepto el ID y el propiedad de @OneToMany, en este caso client )
    public Loan(String name, double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;

    }


    //en tercer lugar se crean los metodos accesores Getters and setter que me permiten acceder y modificar los
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientLoan() {
        return clientLoan;
    }

    public void setClientLoan(Set<ClientLoan> clientLoan) {
        this.clientLoan = clientLoan;
    }
}


