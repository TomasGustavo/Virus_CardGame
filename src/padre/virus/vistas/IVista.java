package padre.virus.vistas;

import padre.virus.gameController.Controlador;

import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);

    void mostrar();

    void mostrarCartas(ArrayList<String> cartas);
    public void mostrarCuerpo(ArrayList<String> organos);
    public void mostrarCuerposEnLista(String jugador,ArrayList<String> jugadores);

    void mostrarTurno(String jugadorActual);

    void mostrarJugadores(ArrayList<String> jugadores);

    void mostrarNuevoJugador(String jugador);
    void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas,ArrayList<String> organos);
    public void cartelInicioPartida();
    public void mostrarTexto(String txt);

    void terminarTurno();

    void HabilitarTurno();
    void partidaTerminada(String jugadorActual);





}
