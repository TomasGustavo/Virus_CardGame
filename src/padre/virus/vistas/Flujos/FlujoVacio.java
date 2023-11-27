package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoVacio extends Flujo{

    public FlujoVacio(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear(" ", Color.white);
    }
}
