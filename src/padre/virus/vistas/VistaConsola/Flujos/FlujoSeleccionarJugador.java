package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoSeleccionarJugador extends Flujo{

    public FlujoSeleccionarJugador(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {

        int opcion = Integer.parseInt(string);
        try{
            if(opcion == 0){
                return new FlujoJugar(vista,controlador);
            }
            String jugadorDestino = controlador.listaJugadores().get(opcion-1);

            return new FlujoSeleccionCarta(vista,controlador,jugadorDestino,false,null);

        } catch (NumberFormatException e){
            vista.printear("\nNumero de jugador fuera de rango\n", ColorRGB.RED);
        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        int i = 1;
        for(String jugador: controlador.listaJugadores()){
            vista.printear(i + " - "+ jugador+"\n", ColorRGB.ORANGE);
            i++;
        }
        vista.printear("-------------------------------------------------------------------\n",ColorRGB.MAGENTA);
        vista.printear("\nSeleccion un jugador para atacar (0 para volver para atras): ",ColorRGB.MAGENTA);
    }
}
