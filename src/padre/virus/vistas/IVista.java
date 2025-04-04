package padre.virus.vistas;

import padre.virus.gameController.Controlador;
import padre.virus.modelo.ICarta;
import padre.virus.modelo.IJugador;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);
    void mostrar();
    void mostrarCartas(ArrayList<ICarta> cartas) throws RemoteException;
    public void mostrarCuerpo(ArrayList<ICarta> organos);
    public void mostrarCuerposEnLista(IJugador jugador,ArrayList<IJugador> jugadores);
    void mostrarTurno(IJugador jugadorActual);
    void mostrarJugadores(ArrayList<IJugador> jugadores);
    void mostrarNuevoJugador(IJugador jugador);
    void mostarInicioPartido(IJugador jugadorActual, ArrayList<ICarta> cartas, ArrayList<ICarta> organos) throws RemoteException;
    void mostrarNuevoJugador();
    public void cartelInicioPartida();
    public void mostrarTexto(String txt);
    public void actualizarMazo(int mazo, int descarte);
    void terminarTurno();
    void HabilitarTurno();
    void partidaTerminada(String jugadorActual);
    void abandonoPartida(IJugador nombre);
    void notificarMensaje(String texto);
    //void printearNotificacion(String jugadorActual, Color color);
    void printear(String texto,Color color);
    void mostrarChat(String texto, IJugador nombreJugador);
}
