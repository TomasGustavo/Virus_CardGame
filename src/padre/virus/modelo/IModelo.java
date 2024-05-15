package padre.virus.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import padre.virus.Save;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IModelo extends IObservableRemoto {
    IJugador agregarJugador(String nombre) throws RemoteException;
    void reiniciar()throws RemoteException;

    void jugar() throws RemoteException;

    IJugador cambiarTurno(int jugadorID) throws RemoteException;

    boolean hayGandor() throws RemoteException;

    IJugador turnoActual() throws RemoteException;

    void tomarCarta(IJugador nombreJugador) throws RemoteException;

    ArrayList<IJugador> obtenerJugadores() throws RemoteException;

    void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException;

    ArrayList<ICarta> obtenerMazo() throws RemoteException;
    public ArrayList<ICarta> obtenerMazoDescarte() throws RemoteException;

    ArrayList<ICarta> obtenerCartas(String nombre) throws RemoteException;

    ArrayList<ICarta> obtenerOrganos(String nombre) throws RemoteException;

    String getGanador() throws RemoteException;
    public String getOponenteLBL(int jugadorID) throws RemoteException;

    public String getOponentePorID(int jugadorID) throws RemoteException;
    void abandonoPartida() throws RemoteException;

    void descartar(IJugador nombreJugador, int opcion) throws RemoteException;

    void terminarTurno(IJugador nombreJugador) throws RemoteException;

    void partidaTerminada(String ganador) throws RemoteException;

    void mostrarChat(String txt,IJugador jugador) throws RemoteException;
    String getMensaje() throws RemoteException;
    IJugador getJug() throws RemoteException;

    void guardarPartida(String nombrePartida)throws RemoteException;
    void cargarPartida(int idPartida,String host)throws RemoteException;
    ArrayList<String> getListaPartidas() throws RemoteException;
    boolean esHost(IJugador jugador) throws RemoteException;
    //void reEscribirPartida(int posicion, String nombreSave)throws RemoteException;
}
