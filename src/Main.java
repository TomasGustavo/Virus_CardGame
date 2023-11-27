import padre.virus.gameController.Controlador;
import padre.virus.modelo.Modelo;
import padre.virus.vistas.VistaConsola;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Modelo modelo = new Modelo();
                    VistaConsola v1 = new VistaConsola();
                    VistaConsola v2 = new VistaConsola();

                    Controlador c1 = new Controlador(modelo,v1);
                    Controlador c2 = new Controlador(modelo,v2);

                    v1.setControlador(c1);
                    v2.setControlador(c2);

                    v1.mostarInicioPartido();
                    v2.mostarInicioPartido();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}