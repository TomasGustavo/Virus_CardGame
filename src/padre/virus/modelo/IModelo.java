package padre.virus.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IModelo extends IObservableRemoto {
    IJugador agregarJugador(String nombre) throws RemoteException;

    void jugar() throws RemoteException;

    IJugador cambiarTurno(int jugadorID) throws RemoteException;

    void hayGandor() throws RemoteException;

    IJugador turnoActual() throws RemoteException;

    void tomarCarta(IJugador nombreJugador) throws RemoteException;

    ArrayList<String> obtenerJugadores() throws RemoteException;

    void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException;

    ArrayList<ICarta> obtenerMazo() throws RemoteException;

    ArrayList<ICarta> obtenerCartas(String nombre) throws RemoteException;

    ArrayList<ICarta> obtenerOrganos(String nombre) throws RemoteException;

    String getGanador() throws RemoteException;
    public String getOponente(int jugadorID) throws RemoteException;

    void abandonoPartida() throws RemoteException;

    void descartar(IJugador nombreJugador, int opcion) throws RemoteException;

    void terminarTurno(IJugador nombreJugador) throws RemoteException;

    void partidaTerminada(String ganador) throws RemoteException;

    void mostrarChat(String txt,String jugador) throws RemoteException;
    String getMensaje() throws RemoteException;
    String getJug() throws RemoteException;
}
