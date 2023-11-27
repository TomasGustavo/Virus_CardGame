package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

public class FlujoMenuPrincipal extends Flujo{

    public FlujoMenuPrincipal(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {

    }
}
