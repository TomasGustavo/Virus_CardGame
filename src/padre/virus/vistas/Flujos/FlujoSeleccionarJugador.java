package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoSeleccionarJugador extends Flujo{

    public FlujoSeleccionarJugador(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        int opcion = Integer.parseInt(string);

        try{
            String jugadorDestino = controlador.listaJugadores().get(opcion-1);

            vista.mostrarCartas(controlador.obtenerCartas());
            

        } catch (NumberFormatException e){
            vista.printear("\nNumero de jugador fuera de rango\n",Color.red);
        }

        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        int i = 1;
        for(String jugador: controlador.listaJugadores()){
            vista.printear(i + " - "+ jugador+"\n", Color.ORANGE);
        }
        vista.printear("-------------------------------------------------------------------\n",Color.MAGENTA);
        vista.printear("\nSeleccion un jugador para atacar: ",Color.MAGENTA);
    }
}
