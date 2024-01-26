package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoPasarTurno extends Flujo{

    public FlujoPasarTurno(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
        controlador.pasoTurno();
    }
    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("\n----------------------------------------------------\n", ColorRGB.MAGENTA);
        vista.printear("Terminaste el turno\n",ColorRGB.ORANGE);
    }
}
