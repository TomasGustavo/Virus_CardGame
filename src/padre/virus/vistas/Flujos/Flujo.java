package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.image.renderable.ContextualRenderedImageFactory;

public abstract class Flujo {

    protected final VistaConsola vista;
    protected final Controlador controlador;

    public Flujo (VistaConsola vista, Controlador controlador){
        this.vista = vista;
        this.controlador = controlador;
    }

    public abstract Flujo procesarEntrada(String string);

    public abstract void mostrarSiguienteTexto();
}
