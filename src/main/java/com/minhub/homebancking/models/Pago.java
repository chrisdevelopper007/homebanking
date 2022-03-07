package com.minhub.homebancking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String numeroDeTarjeta;
    private int cvv;
    private Double monto;
    private String descripción;

    public Pago() {
    }

    public Pago(Long id, String numeroDeTarjeta, int cvv, Double monto, String descripción) {
        this.id = id;
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.cvv = cvv;
        this.monto = monto;
        this.descripción = descripción;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", numeroDeTarjeta='" + numeroDeTarjeta + '\'' +
                ", cvv=" + cvv +
                ", monto=" + monto +
                ", descripción='" + descripción + '\'' +
                '}';
    }
}
