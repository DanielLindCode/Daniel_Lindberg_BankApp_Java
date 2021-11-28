package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Skapar min bank
        Bank GritBank = new Bank("GritBank");

        //Lägger till kunder
        GritBank.addCustomer( "Daniel", "Lindberg", 8803213993L);
        GritBank.addCustomer( "Louise", "Sörensen", 9132153993L);
        GritBank.addCustomer( "Peter", "Fiskman", 8802215668L);


        //Skapar tre konton till kunden Daniel
        GritBank.addAccount(8803213993L, "Debit");
        GritBank.addAccount(8803213993L, "Debit");
        GritBank.addAccount(8803213993L, "Debit");

        //Skapar två konton till Louise
        GritBank.addAccount(9132153993L, "Debit");
        GritBank.addAccount(9132153993L, "Debit");

        //Skapar ett till Peter
        GritBank.addAccount(8802215668L, "Debit");



        // Printar infon
        System.out.println(GritBank.getAccounts(8803213993L));
        System.out.println("****************************************");
        System.out.println(GritBank.getAccounts(9132153993L));
        System.out.println("****************************************");
        System.out.println(GritBank.getAccounts(8802215668L));
        System.out.println("****************************************");


        //Lägger till pengar till Daniels konto
        GritBank.deposit(8803213993L,1001L,5000);

        //Printar den nya infon
        System.out.println(GritBank.getAccounts(8803213993L));

        System.out.println("****************************************");

        /*
        Plötsligt slutade koden att fungera vid closeAccount-metoden.
         */

        //Tar bort ett av kontona
        GritBank.closeAccount(8803213993L, 1002L);

        //Printar ny info om Daniels konton
       System.out.println(GritBank.getAccounts(8803213993L));

        System.out.println("****************************************");

       //Häntar ut lite pengar från kontona
        GritBank.withdraw(8803213993L,1001L, 350);
        GritBank.withdraw(8803213993L, 1003L, 15);

        //Visar kontona ser ut nu.
        System.out.println(GritBank.getAccounts(8803213993L));

        System.out.println("****************************************");

        //Printar alla kunder till en .txt
        GritBank.printCustomers();


        System.out.println(GritBank.getCustomer(8803213993L));

        //Ändrar namnet
        GritBank.changeCustomerName("Per", "Andersson", 8803213993L);

        System.out.println("****************************************");

        System.out.println(GritBank.getCustomer(8803213993L));

        System.out.println("****************************************");


        /*
        Koden slutade fungera när jag satt med det igår natt och jag förstår inte vad som hände.
        Min metod för att hitta ett account index är felet men jag hinner inte fixa det innan inlämning.
       */

        // Tar bort kunden
        //System.out.println(GritBank.removeCustomer(8802215668L));

       // System.out.println(GritBank.getAccount(8803213993L, 1001L));




    }
}
