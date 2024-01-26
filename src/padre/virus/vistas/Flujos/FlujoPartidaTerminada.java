package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
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

        if(string.equals("0")){
            return new FlujoMenuPrincipal(vista,controlador);
        }

        return new FlujoVacio(vista,controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.habEntradas();
        vista.printear("---------------------------------------------------------------", ColorRGB.MAGENTA);
        if(ganador){
            vista.printear("\n\n\n\t\tPartida terminada\n\n\t\tEl ganador es " + jugador + "\n\n", ColorRGB.GREEN);
        }
        else{
            vista.printear("\n\n\n\t\t   Partida terminada\n\n\t\t" + jugador + " termino la partida\n\n", ColorRGB.GREEN);
        }
        vista.printear("\t PRESIONE [0] PARA VOLVER AL MENU PRINCIPAL!!\n",ColorRGB.RED);
    }
}
