package com.ponscio.view.interfaz;
import com.ponscio.util.Scan;

public interface IMenu {
    public Scan scan = new Scan();
    public void iniciar();
    public void leerOpcion(int opcion);
    public void mostrarMenu();
}
