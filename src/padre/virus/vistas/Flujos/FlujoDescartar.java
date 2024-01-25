package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoDescartar extends Flujo{

    public FlujoDescartar(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        try{
            int opcion = Integer.parseInt(string);

            if(opcion >=1 && opcion <= controlador.obtenerCartas().size()){
                controlador.descartar(opcion-1);
                //controlador.actualizarMano();
                controlador.terminoTurno();
                return new FlujoEsperandoTurno(vista,controlador);
            }
        } catch (NumberFormatException e){
            vista.printear("El numero de carta no es valido \n",Color.red);
        }

        return this;

    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarCartas(controlador.obtenerCartas());
        vista.printear("\n--------------------------------------------\n", Color.MAGENTA);
        vista.printear("\nSeleccione carta a descartar\n", Color.MAGENTA);

    }
}
