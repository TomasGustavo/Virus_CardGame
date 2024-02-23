package padre.virus.vistas.VistaConsola.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.VistaConsola.VistaConsola;

public class FlujoGuardarPartida extends Flujo{
    public FlujoGuardarPartida(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {

        if(controlador.getPartidasGuardadas().size() < 4){
            controlador.guardarPartida(string);
            vista.printear("Partida Guardada con Exito!",ColorRGB.GREEN);
        }

        return new FlujoJugar(vista,controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
            if(controlador.getPartidasGuardadas().size() < 4){
                int j=0;
                for(String i : controlador.getPartidasGuardadas()){
                    vista.printear(j+" - "+i, ColorRGB.ORANGE);
                    j++;
                }
                vista.printear("Ingresar el nombre que tendra la Partida a guardar: ", ColorRGB.DARK_TIEL);
            }else{
                vista.printear("No hay Slots disponibles para guardar!",ColorRGB.RED);
            }
    }
}
