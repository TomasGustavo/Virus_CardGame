package padre.virus.vistas.VistaGrafica;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.IVista;

import javax.swing.*;
import java.util.ArrayList;

public class VistaGrafica implements IVista {
    private JPanel panel1;
    private JLabel lblNotificaciones;
    private JSplitPane splitPrincipal;
    private JPanel pnlMenu;
    private JPanel pnlCardNuevoJugador;
    private JPanel pnlContenedor;
    private JTextField txtNombreJugador;
    private JButton Aceptar;
    private JLabel lblNombreJugador;

    @Override
    public void setControlador(Controlador controlador) {

    }

    @Override
    public void mostrar() {

    }

    @Override
    public void mostrarCartas(ArrayList<String> cartas) {

    }

    @Override
    public void mostrarCuerpo(ArrayList<String> organos) {

    }

    @Override
    public void mostrarCuerposEnLista(String jugador, ArrayList<String> jugadores) {

    }

    @Override
    public void mostrarTurno(String jugadorActual) {

    }

    @Override
    public void mostrarJugadores(ArrayList<String> jugadores) {

    }

    @Override
    public void mostrarNuevoJugador(String jugador) {

    }

    @Override
    public void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas, ArrayList<String> organos) {

    }

    @Override
    public void mostrarNuevoJugador() {

    }

    @Override
    public void cartelInicioPartida() {

    }

    @Override
    public void mostrarTexto(String txt) {

    }

    @Override
    public void terminarTurno() {

    }

    @Override
    public void HabilitarTurno() {

    }

    @Override
    public void partidaTerminada(String jugadorActual) {

    }

    @Override
    public void abandonoPartida(String nombre) {

    }

    @Override
    public void notificarMensaje(String texto) {

    }
}
