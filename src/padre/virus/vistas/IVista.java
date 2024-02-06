package padre.virus.vistas;

import padre.virus.gameController.Controlador;
import padre.virus.modelo.ICarta;

import java.awt.*;
import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);
    void mostrar();
    void mostrarCartas(ArrayList<ICarta> cartas);
    public void mostrarCuerpo(ArrayList<ICarta> organos);
    public void mostrarCuerposEnLista(String jugador,ArrayList<String> jugadores); // TODO convertidor a IJugador
    void mostrarTurno(String jugadorActual); // TODO convertidor a IJugador
    void mostrarJugadores(ArrayList<String> jugadores); // TODO convertidor a IJugador
    void mostrarNuevoJugador(String jugador); // TODO convertidor a IJugador
    void mostarInicioPartido(String jugadorActual, ArrayList<ICarta> cartas,ArrayList<ICarta> organos); // TODO convertidor a IJugador
    void mostrarNuevoJugador();
    public void cartelInicioPartida();
    public void mostrarTexto(String txt);
    void terminarTurno();
    void HabilitarTurno();
    void partidaTerminada(String jugadorActual);
    void abandonoPartida(String nombre); // TODO convertidor a IJugador
    void notificarMensaje(String texto);
    //void printearNotificacion(String jugadorActual, Color color);
    void printear(String texto,Color color);
    void mostrarChat(String texto, String nombreJugador); // TODO convertidor a IJugador
}
