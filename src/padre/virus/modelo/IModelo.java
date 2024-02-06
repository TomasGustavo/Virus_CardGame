package padre.virus.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IModelo extends IObservableRemoto {
    void agregarJugador(String nombre) throws RemoteException;

    void jugar() throws RemoteException;

    String cambiarTurno(int jugadorID) throws RemoteException;

    void hayGandor() throws RemoteException;

    String turnoActual() throws RemoteException;

    void tomarCarta(String nombreJugador) throws RemoteException;

    ArrayList<String> obtenerJugadores() throws RemoteException;

    void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException;

    ArrayList<ICarta> obtenerMazo() throws RemoteException;

    ArrayList<ICarta> obtenerCartas(String nombre) throws RemoteException;

    ArrayList<ICarta> obtenerOrganos(String nombre) throws RemoteException;

    String getGanador() throws RemoteException;

    void abandonoPartida() throws RemoteException;

    void descartar(String nombreJugador, int opcion) throws RemoteException;

    void terminarTurno(String nombreJugador) throws RemoteException;

    void partidaTerminada(String ganador) throws RemoteException;

    void mostrarChat(String txt,String jugador) throws RemoteException;
    String getMensaje() throws RemoteException;
    String getJug() throws RemoteException;
}
