package com.ponscio.util;
import java.math.BigDecimal;
import java.util.Scanner;

public class Scan {
    
    private Scanner scan;

    public Scan() {
        this.scan = new Scanner(System.in);
    }

    public String leerTexto(String msg) {
        System.out.println("\n" + msg);
        String text = scan.nextLine();
        if (text.length() < 1) return null;
        return text;
    }    

    public int leerInt(String msg) {
        System.out.println("\n" + msg);
        try {
            String opcion = scan.nextLine();
            if (opcion == null) return -1;
            return Integer.parseInt(opcion);
        } catch (Exception e) {
            System.out.println("\nPosible Error: El valor ingresado debe ser de tipo numerico");
            return -1;
        }
    }

    public double leerDouble(String msg) {
        System.out.println("\n" + msg);
        try {
            String opcion = scan.nextLine();
            if (opcion == null) return -1;
            return Double.parseDouble(opcion);
        } catch (Exception e) {
            System.out.println("\nPosible Error: El valor ingresado debe ser de tipo double");
            return -1;
        }
    }

    public BigDecimal leerBigDecimal(String msg) {
        System.out.println("\n" + msg);
        try {
            BigDecimal opcion = scan.nextBigDecimal();
            return opcion;
        } catch (Exception e) {
            System.out.println("\nPosible Error: El valor ingresado debe ser de tipo double");
            return BigDecimal.valueOf(0);
        }
    }
    

}
