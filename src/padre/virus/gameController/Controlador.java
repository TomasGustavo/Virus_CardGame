package padre.virus.gameController;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import padre.virus.modelo.*;
import padre.virus.vistas.IVista;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements IControladorRemoto {

    private IModelo modelo;
    private IVista vista;
    private String nombreJugador;
    String jugadorActual;
    int idJA;
    String Ganador;
    private boolean partidaIniciada = false;
    private ArrayList<ICarta> mazo;
    private ArrayList<String> jugadores;
    private ArrayList<ICarta> cartas;
    private ArrayList<ICarta> organos;
    private ArrayList<ICarta> descarte;

    public Controlador(IVista vista){

        this.vista = vista;
        this.vista.setControlador(this);

        jugadores = new ArrayList<>();
        descarte = new ArrayList<>();
        cartas = new ArrayList<>();
        idJA = 0;
    }

    public void Jugar(){
        try {
            this.modelo.jugar();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void actualizar(IObservableRemoto modelo, Object evento) throws RemoteException{
        if(evento instanceof Eventos){
            switch((Eventos) evento) {
                case NUEVO_JUGADOR -> {
                    jugadores = this.modelo.obtenerJugadores();
                    this.vista.mostrarTexto("El Jugador "+jugadores.get(jugadores.size() - 1 )+" se ha unido a la partida\n");
                }
                case PARTIDA_INICIADA -> {
                    cartas = this.modelo.obtenerCartas(nombreJugador);
                    organos = this.modelo.obtenerOrganos(nombreJugador);
                    jugadorActual = this.modelo.turnoActual();
                    mazo = this.modelo.obtenerMazo();
                    idJA = 0;
                    this.vista.cartelInicioPartida();
                    this.vista.mostarInicioPartido(jugadorActual,cartas,organos);
                }
                case ROBO_CARTA -> {
                    cartas = this.modelo.obtenerCartas(nombreJugador);
                }
                case TERMINO_TURNO -> {
                    cartas = this.modelo.obtenerCartas(nombreJugador);
                    if(jugadorActual.equals(nombreJugador)){
                        vista.mostrarTurno(jugadorActual);
                        this.modelo.tomarCarta(nombreJugador);
                        vista.mostrarCuerposEnLista(nombreJugador,jugadores);
                        vista.mostrarCuerpo(this.modelo.obtenerOrganos(nombreJugador));
                        vista.mostrarCartas(cartas);
                        vista.terminarTurno();
                    }
                    jugadorActual = this.modelo.cambiarTurno(idJA);
                    vista.notificarMensaje("Es turno del jugador: " + jugadorActual);
                    if(jugadorActual.equals(nombreJugador)){
                        vista.mostrarTurno(jugadorActual);
                        this.modelo.tomarCarta(nombreJugador);
                        vista.mostrarCuerposEnLista(nombreJugador,jugadores);
                        vista.mostrarCuerpo(this.modelo.obtenerOrganos(nombreJugador));
                        vista.mostrarCartas(cartas);
                        vista.HabilitarTurno();
                    }
                    if(idJA == jugadores.size() - 1){
                        idJA = 0;
                    }
                    else{
                        idJA++;
                    }

                }
                case PARTIDA_FINALIZADA -> {
                    Ganador = this.modelo.getGanador();
                    vista.partidaTerminada(Ganador);
                }
                case ABANDONO_PARTIDA -> {
                    vista.abandonoPartida(nombreJugador);
                }

                case MENSAJE_CHAT -> {

                    vista.mostrarChat(this.modelo.getMensaje(), this.modelo.getJug());
                }

            }
        }
    }

    public ArrayList<String> listaJugadores(){
        return jugadores;
    }

    public int obtenerMazo() {

        try {
            return modelo.obtenerMazo().size();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void AgregarJugador(String nombre){
        try {
            this.nombreJugador = nombre;
            modelo.agregarJugador(nombre);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public String getNombre() {
        return nombreJugador;
    }

    public String getJugadorActual(){
        return jugadorActual;
    }

    //public void partidaTerminada(String nombreGanador) {
    //    modelo.partidaTerminada(nombreGanador);
    //}

    public boolean isPartidaIniciada(){
        return partidaIniciada;
    }

    public boolean isTurno(){
        return nombreJugador.equals(jugadorActual);
    }

    public ArrayList<ICarta> obtenerCartas(){
        return cartas;
    }

    public void actualizarMano(){
        try {
            cartas = this.modelo.obtenerCartas(nombreJugador);
            vista.mostrarCartas(cartas);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
    public void descartar(int opcion){
        try {
            modelo.descartar(nombreJugador,opcion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void terminoTurno(){
        try {
            modelo.cambiarTurno(idJA);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void pasoTurno(){
        try {
            modelo.terminarTurno(nombreJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void tomarCarta(){
        try {
            modelo.tomarCarta(nombreJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void tirarCarta(String jugadorDestino,Integer idCarta,int idOrgano){
        try {
            modelo.tirarCarta(jugadorActual,jugadorDestino,idCarta,idOrgano);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ICarta> obtenerOrganos(String jugadorDestino){
        try {
            return modelo.obtenerOrganos(jugadorDestino);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public void terminoPartida(){
        try {
            modelo.hayGandor();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public String abandonoPartida(){
        try {
            modelo.abandonoPartida();
            return nombreJugador;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarChat(String txt, String jugador){
        try {
            for(String j: jugadores){
                if(jugador.equals(j)){

                    modelo.mostrarChat(txt,j);
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IModelo) modeloRemoto;
    }


}
