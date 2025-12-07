package com.ponscio.util;
import java.util.Scanner;

public class Scan {
    
    private Scanner scan;

    public Scan() {
        this.scan = new Scanner(System.in);
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

    public double leerDouble(String msg) {
        System.out.println("\n" + msg);
        try {
            String opcion = scan.nextLine();
            return Double.parseDouble(opcion);
        } catch (Exception e) {
            System.out.println("\nError: El valor ingresado debe ser de tipo double");
            return -1;
        }
    }
    

}
