package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoNuevoUsuario extends Flujo{
    public FlujoNuevoUsuario(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }

    @Override
    public Flujo procesarEntrada(String jugador) {
        if(controlador.isPartidaIniciada()){
            vista.printear("La partida Comenzó", Color.green);
        } else{
            if(controlador.listaJugadores().size() == 6){
                vista.printear("La cantidad de jugadores esta en su maximo (6 jugadores)", Color.ORANGE);
            } else {
                if(!jugador.trim().isEmpty()){
                    //vista.printear("Jugador: " + jugador + "se conecto con exito\n",Color.green);
                    controlador.AgregarJugador(jugador);
                    return new FlujoMenuPrincipal(vista,controlador);
                } else{
                    vista.printear("El nombre no es valido", Color.red);
                }
            }
        }
        return new FlujoPartidaTerminada(vista,controlador,controlador.getNombre(),false);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("--------------------------------------------------------------------",Color.MAGENTA);
        vista.printear("|                                                                   |",Color.MAGENTA);
        vista.printear("--------------------------------------------------------------------\n",Color.MAGENTA);
        vista.printear("Ingrese Nombre del jugador: ",Color.MAGENTA);
    }
}
