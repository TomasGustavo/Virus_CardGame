package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoAbandonarPartida extends Flujo{

    public FlujoAbandonarPartida(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        return new FlujoPartidaTerminada(vista,controlador, controlador.getNombre(),false);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("El jugador ha abandonado la partida\n", ColorRGB.RED);
    }
}
