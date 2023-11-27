package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoJugar extends Flujo{

    public FlujoJugar(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {
        switch(string){
            case "1" ->{
                if(controlador.listaJugadores().size() < 2){
                    vista.printear("NO hay mas jugadores, solo usted\n",Color.MAGENTA);
                } else{
                    return new FlujoSeleccionarJugador(vista,controlador);
                }
            }
            case "2" ->{
                return new FlujoDescartar(vista,controlador);
            }
            case "3" ->{
                return new FlujoAbandonarPartida(vista,controlador);
            }
            default -> vista.printear("Opcion invalida\n",Color.red);

        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarCartas(controlador.obtenerCartas());
        vista.printear("1 - hacer jugada\n", Color.MAGENTA);
        vista.printear("2 - descartar cartas\n", Color.MAGENTA);
        vista.printear("3 - abandonar partida\n", Color.MAGENTA);
        vista.printear("-----------------------------------------------\n", Color.MAGENTA);
        vista.printear("seleccionar opcion: \n", Color.MAGENTA);
    }
}
