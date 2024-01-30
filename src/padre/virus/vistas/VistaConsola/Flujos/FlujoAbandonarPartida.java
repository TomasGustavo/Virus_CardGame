package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoAbandonarPartida extends Flujo{
    private String nombre;

    public FlujoAbandonarPartida(VistaConsola vista, Controlador controlador,String nombre){
        super(vista,controlador);
        this.nombre = nombre;
    }
    @Override
    public Flujo procesarEntrada(String string) {

        return new FlujoPartidaTerminada(vista,controlador, nombre,false);
    }

    @Override
    public void mostrarSiguienteTexto() {
        //vista.printear("Has abandonado la partida\n", ColorRGB.RED);
    }
}
