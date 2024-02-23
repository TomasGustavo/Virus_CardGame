package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoCargarPartida extends Flujo{
    public FlujoCargarPartida(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {

        int opc = Integer.parseInt(string);
        if(opc>=0||opc<=4){
            controlador.cargarPartida(opc);
            vista.printear("Partida cargada con exito, cuando inicia la nueva partida, seran los datos guardados",ColorRGB.GREEN);
        }
        return new FlujoMenuPrincipal(vista,controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
        int j=0;
        for(String i : controlador.getPartidasGuardadas()){
            vista.printear(j+" - "+i, ColorRGB.ORANGE);
            j++;
        }
        vista.printear("\nSeleccione el numero de Slot a cargar: ",ColorRGB.DARK_TIEL);
    }
}
