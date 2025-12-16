package com.ponscio.repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BufferWriter {
    private final String ruta;

    public BufferWriter(String ruta) {
        this.ruta = ruta;
    }
    
    public void escribir(String texto) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {

            bw.write("\n" + LocalDateTime.now().format(formato));
            bw.write(texto);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir el registro " + e.getMessage());
        }
    }

}
