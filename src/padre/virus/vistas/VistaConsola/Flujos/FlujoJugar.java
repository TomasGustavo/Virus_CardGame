package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.modelo.IJugador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoJugar extends Flujo{

    public FlujoJugar(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        switch(string){
            case "1" ->{
                if(controlador.listaJugadores().size() < 2){
                    vista.printear("No hay mas jugadores, solo usted\n", ColorRGB.MAGENTA);
                } else{
                    return new FlujoSeleccionarJugador(vista,controlador);
                }
            }
            case "2" ->{

                return new FlujoDescartar(vista,controlador);
            }
            case "3" -> {
                return new FlujoPasarTurno(vista,controlador);
            }
            case "4" -> {
                return new FlujoGuardarPartida(vista,controlador);
            }
            case "5" ->{
                String nombre = controlador.abandonoPartida().getNombre();
                return new FlujoAbandonarPartida(vista,controlador,nombre);

            }
            default -> vista.printear("Opcion invalida\n",ColorRGB.RED);

        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        //vista.mostrarCartas(controlador.obtenerCartas());
        vista.printear("\n\n1 - Hacer jugada\n", ColorRGB.MAGENTA);
        vista.printear("2 - Descartar cartas\n", ColorRGB.MAGENTA);
        vista.printear("3 - Terminar turno\n", ColorRGB.MAGENTA);
        vista.printear("4 - Guardar partida\n", ColorRGB.MAGENTA);
        vista.printear("5 - Abandonar partida\n", ColorRGB.MAGENTA);
        vista.printear("-----------------------------------------------\n", ColorRGB.MAGENTA);
        vista.printear("seleccionar opcion: \n", ColorRGB.CYAN);
    }
}
