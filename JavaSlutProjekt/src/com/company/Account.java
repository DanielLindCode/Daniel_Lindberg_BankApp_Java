package com.company;

public class Account {

    //States
    private String accountType;
    private Long accountNumber;
    private int saldo;

    //Constructor

    // Jag ville läsa in från en textfil och instansiera från denna constructorn men jag hann inte göra klart inmatningen.
    public Account(String aType, Long aNumber, int saldo)
    {
        this.accountType=aType;
        this.accountNumber=aNumber;
        this.saldo=saldo;
    }
    public Account(String aType, Long aNumber)
    {
        this.accountType=aType;
        this.accountNumber=aNumber;
    }
    public Account(String aType)
    {
        this.accountType=aType;
    }

    //Getters & Setters
    public String getAccountType() {
        return accountType;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    //Behavior

    public void createAccountNumber(long aNumber){
        this.accountNumber=aNumber;
    }
}
