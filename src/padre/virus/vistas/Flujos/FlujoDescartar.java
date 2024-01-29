package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FlujoDescartar extends Flujo{

    public FlujoDescartar(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        try{

            String[] seleccion = string.split(",");
            int[] selecionInt = new int[seleccion.length];

            for(int i =0;i<seleccion.length;i++){
                selecionInt[i] = Integer.parseInt(seleccion[i]);
            }
            Arrays.sort(seleccion,Collections.reverseOrder());

            for(int indexCarta : selecionInt){
                    controlador.descartar(indexCarta-1);
            }

            controlador.pasoTurno();
            return new FlujoEsperandoTurno(vista,controlador);

        } catch (NumberFormatException e){
            vista.printear("El numero de carta no es valido \n", ColorRGB.RED);
        }

        return this;

    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarCartas(controlador.obtenerCartas());
        vista.printear("\n--------------------------------------------\n", ColorRGB.MAGENTA);
        vista.printear("\nSeleccione carta/s a descartar\n", ColorRGB.MAGENTA);
        vista.printear("Separadas con coma ',' ejemplo: 1,2,3 / 1,3,2 / 3,2 ....", ColorRGB.MAGENTA);


    }
}
