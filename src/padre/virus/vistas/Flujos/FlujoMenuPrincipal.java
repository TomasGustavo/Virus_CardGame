package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoMenuPrincipal extends Flujo{

    public FlujoMenuPrincipal(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {
        switch (string){
            case "1" ->{
                controlador.Jugar();
                if(controlador.isTurno()){
                    return new FlujoJugar(vista,controlador);
                } else{
                    return new FlujoVacio(vista,controlador);
                }

            }
            case "2" -> {
                mostrarJugadores();
            }
            case "3" -> {
                return new FlujoMostrarReglas(vista,controlador);
            }

        }

        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("--------------------------------------------------------------------\n", ColorRGB.MAGENTA);
        vista.printear("|                             Menu principal                       |\n",ColorRGB.MAGENTA);
        vista.printear("--------------------------------------------------------------------\n",ColorRGB.MAGENTA);
        vista.printear("1 - iniciar partida \n",ColorRGB.MAGENTA);
        vista.printear("2 - mostrar jugadores \n",ColorRGB.MAGENTA);
        vista.printear("3 - mostrar reglas \n",ColorRGB.MAGENTA);

    }

    private void mostrarJugadores(){
        vista.printear("\nJugadores: \n",Color.white);
        for(String jugador : controlador.listaJugadores()){
            vista.printear("- "+ jugador + "\n",ColorRGB.ORANGE);
        }
        vista.printear("\n",Color.white);
    }
}
