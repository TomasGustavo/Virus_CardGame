package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoEsperandoTurno extends Flujo{

    public FlujoEsperandoTurno(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
            vista.desEntradas();
            vista.printear("\nEspere el cambio de turno\n", Color.red);
    }
}
