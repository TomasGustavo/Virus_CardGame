package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoNuevoUsuario extends Flujo{
    public FlujoNuevoUsuario(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }

    @Override
    public Flujo procesarEntrada(String jugador) {
        if(controlador.isPartidaIniciada()){
            vista.printear("La partida Comenzó", ColorRGB.GREEN);
        } else{
            if(controlador.listaJugadores().size() == 6){
                vista.printear("La cantidad de jugadores esta en su maximo (6 jugadores)", ColorRGB.ORANGE);
            } else {
                if(!jugador.trim().isEmpty()){
                    //vista.printear("Jugador: " + jugador + "se conecto con exito\n",Color.green);
                    controlador.AgregarJugador(jugador);
                    return new FlujoMenuPrincipal(vista,controlador);
                } else{
                    vista.printear("El nombre no es valido", ColorRGB.RED);
                }
            }
        }
        return new FlujoPartidaTerminada(vista,controlador,controlador.getNombre(),false);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("--------------------------------------------------------------------\n",ColorRGB.MAGENTA);
        vista.printear("|                      BIENVENIDO A VIRUS!                          |\n",ColorRGB.MAGENTA);
        vista.printear("--------------------------------------------------------------------\n",ColorRGB.MAGENTA);
        vista.printear("Ingrese Nombre del jugador: ",ColorRGB.MAGENTA);
    }
}
