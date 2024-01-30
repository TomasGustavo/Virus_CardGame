package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoSeleccionCarta extends Flujo{

    private final String jugadorDestino;
    private final boolean cartaOrgano;
    private final Integer cartaSelect;
    public FlujoSeleccionCarta(VistaConsola vista, Controlador controlador, String jugadorDestino,Boolean carta,Integer cartaSelect){
        super(vista, controlador);
        this.jugadorDestino = jugadorDestino;
        this.cartaOrgano = carta;
        this.cartaSelect = cartaSelect;
    }

    @Override
    public Flujo procesarEntrada(String string) {

        // chequea si lo que muestra son las cartas del jugador con turno o los organos del jugador
        // destino a atacar

        int opcion = Integer.parseInt(string);
        try{
            // si son cartas del jugador actual, selecciona una, la guarda y llama al mismo flujo
            // cambiando el boolean para que ahora muestr si o si los organos de jugador a atacar
            if(!cartaOrgano){
                if(opcion >=1 && opcion <= controlador.obtenerCartas().size()){

                    return new FlujoSeleccionCarta(vista,controlador,jugadorDestino,true,opcion);
                }
            } else if (cartaOrgano) {
                // selecciona el organo y llama al controlador para hacer la jugado con todos los datos
                if(opcion >=1 && opcion <= controlador.obtenerOrganos(jugadorDestino).size()){

                    controlador.tirarCarta(jugadorDestino,cartaSelect,opcion);

                }
            }


        } catch (NumberFormatException e){
            vista.printear("Opcion invalida \n", ColorRGB.RED);
        }

        return new FlujoEsperandoTurno(vista,controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
        if(!cartaOrgano){
            vista.mostrarCartas(controlador.obtenerCartas());
        } else {
            vista.mostrarCuerpoRival(controlador.obtenerOrganos(jugadorDestino));
        }

    }
}
