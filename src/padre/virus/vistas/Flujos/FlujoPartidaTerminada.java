package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoPartidaTerminada extends Flujo{
    private String jugador;
    private boolean ganador;

    public FlujoPartidaTerminada(VistaConsola vista, Controlador controlador, String jugador, boolean ganador) {
        super(vista, controlador);
        this.jugador = jugador;
        this.ganador = ganador;
    }

    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.desEntradas();
        vista.printear("---------------------------------------------------------------", Color.MAGENTA);
        if(ganador){
            vista.printear("\nPartida terminada\nEl ganador es " + jugador + " ", Color.MAGENTA);
        }
        else{
            vista.printear("\nPartida terminada\n" + jugador + " termino la partida ", Color.MAGENTA);
        }
    }
}
