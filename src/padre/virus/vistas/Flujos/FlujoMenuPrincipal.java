package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
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
        vista.printear("--------------------------------------------------------------------\n", Color.MAGENTA);
        vista.printear("|                             Menu principal                       |\n",Color.MAGENTA);
        vista.printear("--------------------------------------------------------------------\n",Color.MAGENTA);
        vista.printear("1 - iniciar partida \n",Color.MAGENTA);
        vista.printear("2 - mostrar jugadores \n",Color.MAGENTA);
        vista.printear("3 - mostrar reglas \n",Color.MAGENTA);

    }

    private void mostrarJugadores(){
        vista.printear("\nJugadores: \n",Color.white);
        for(String jugador : controlador.listaJugadores()){
            vista.printear("- "+ jugador + "\n",Color.ORANGE);
        }
        vista.printear("\n",Color.white);
    }
}
