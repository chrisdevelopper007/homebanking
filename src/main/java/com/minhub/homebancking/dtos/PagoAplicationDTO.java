package com.minhub.homebancking.dtos;

public class PagoAplicationDTO {
    private String numeroDeTarjeta;
    private int cvv;
    private Double monto;
    private String descripción;
    private String cuentaADebitar;

    public PagoAplicationDTO() {
    }

    public PagoAplicationDTO(String numeroDeTarjeta, int cvv, Double monto, String descripción, String cuentaADebitar) {
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.cvv = cvv;
        this.monto = monto;
        this.descripción = descripción;
        this.cuentaADebitar = cuentaADebitar;
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

    public String getCuentaADebitar() {
        return cuentaADebitar;
    }

    public void setCuentaADebitar(String cuentaADebitar) {
        this.cuentaADebitar = cuentaADebitar;
    }
}
