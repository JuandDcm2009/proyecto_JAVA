package com.ponscio.service;
import java.util.Scanner;

public class Scan {
    
    private static Scan instance;
    private Scanner scan;

    public static Scan getInstance() {
        if (instance == null) {
            instance = new Scan(new Scanner(System.in));
        }
        return instance;
    }

    public Scan(Scanner scan) {
        this.scan = scan;
    }

    public String leerTexto(String msg) {
        System.out.println("\n" + msg);
        return scan.nextLine();
    }    

    public int leerInt(String msg) {
        System.out.println("\n" + msg);
        try {
            String opcion = scan.nextLine();
            return Integer.parseInt(opcion);
        } catch (Exception e) {
            System.out.println("\nError: El valor ingresado debe ser de tipo numerico");
            return -1;
        }
    }

}
