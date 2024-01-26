package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola;

import java.awt.*;
import java.util.Arrays;

public class FlujoDescartar extends Flujo{

    public FlujoDescartar(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        try{
            String[] seleccion = string.split(",");
            int incremento =0;
            //= Integer.parseInt(string);

            //if(opcion >=1 && opcion <= controlador.obtenerCartas().size()){
            for(String indexCarta : seleccion){
                    int posicion = Integer.parseInt(indexCarta) - incremento;
                    controlador.descartar(posicion-1);
                    incremento++;
            }
            //controlador.descartar(opcion-1);
            //controlador.actualizarMano();
            controlador.pasoTurno();
            return new FlujoEsperandoTurno(vista,controlador);
            //}
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
        vista.printear("Separadas con coma ',' ejemplo: 1,2,3 / 1,3", ColorRGB.MAGENTA);


    }
}
