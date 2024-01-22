package padre.virus.vistas;

import padre.virus.gameController.Controlador;

import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);

    void mostrar();

    void mostrarCartas(ArrayList<String> cartas);
    public void mostrarCuerpo(ArrayList<String> organos);

    void mostrarTurno(String jugadorActual);

    void mostrarJugadores(ArrayList<String> jugadores);

    void mostrarNuevoJugador(String jugador);
    void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas,ArrayList<String> organos);

    void terminarTurno();

    void HabilitarTurno();
    void partidaTerminada(String jugadorActual);





}
