package com.minhub.homebancking.dtos;



import java.util.ArrayList;
import java.util.List;


public class LoanAplicationDTO {


    private Double amount;
    private Integer payment;
    private String destinyAccount;
    private String name;
    private Double maxAmount;
    private List<Integer> payments = new ArrayList<>();


    public LoanAplicationDTO() {
    }

    public LoanAplicationDTO(Double amount, Integer payment, String destinyAccount, String name, Double maxAmount, List<Integer> payments) {
        this.amount = amount;
        this.payment = payment;
        this.destinyAccount = destinyAccount;
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(String destinyAccount) {
        this.destinyAccount = destinyAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "LoanAplicationDTO{" +
                "amount=" + amount +
                ", payment=" + payment +
                ", destinyAccount='" + destinyAccount + '\'' +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                '}';
    }
}
