package padre.virus.gameController;

import padre.virus.modelo.*;
import padre.virus.observer.Observable;
import padre.virus.observer.Observador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.IVista;
import padre.virus.vistas.VistaConsola;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements Observador {

    private Modelo modelo;
    private IVista vista;
    private String nombreJugador;
    String jugadorActual;
    int idJA;
    String Ganador;
    private boolean partidaIniciada = false;
    private ArrayList<String> mazo;
    private ArrayList<String> jugadores;
    private ArrayList<String> cartas;
    private ArrayList<String> organos;
    private ArrayList<String> descarte;

    public Controlador(Modelo modelo, IVista vista){

        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);

        jugadores = new ArrayList<>();
        descarte = new ArrayList<>();
        cartas = new ArrayList<>();
        idJA = 0;
    }

    public void Jugar(){
        modelo.jugar();
    }


    @Override
    public void actualizar(Object evento, Observable observado) {
        if(evento instanceof Eventos){
            switch((Eventos) evento) {
                case NUEVO_JUGADOR -> {
                    jugadores = this.modelo.obtenerJugadores();
                    //this.vista.mostrarNuevoJugador(jugadores.get(jugadores.size() - 1 ));
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
                        modelo.tomarCarta(nombreJugador);
                        vista.mostrarCuerposEnLista(nombreJugador,jugadores);
                        vista.mostrarCuerpo(this.modelo.obtenerOrganos(nombreJugador));
                        vista.mostrarCartas(cartas);
                        vista.terminarTurno();
                    }
                    jugadorActual = modelo.cambiarTurno(idJA);
                    if(jugadorActual.equals(nombreJugador)){
                        vista.mostrarTurno(jugadorActual);
                        modelo.tomarCarta(nombreJugador);
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
                    Ganador = modelo.getGanador();
                    vista.partidaTerminada(Ganador);
                }
                case ABANDONO_PARTIDA -> {
                    vista.abandonoPartida(nombreJugador);
                }
                case USO_TRATAMIENTO -> {

                }

            }
        }
    }

    public ArrayList<String> listaJugadores(){
        return jugadores;
    }

    public int obtenerMazo(){
        return modelo.obtenerMazo().size();
    }

    public void AgregarJugador(String nombre){
        modelo.agregarJugador(nombre);
        this.nombreJugador = nombre;
    }

    public String getNombre() {
        return nombreJugador;
    }

    public String getJugadorActual(){
        return jugadorActual;
    }

    public void partidaTerminada(String nombreGanador) {
        modelo.partidaTerminada(nombreGanador);
    }

    public boolean isPartidaIniciada(){
        return partidaIniciada;
    }

    public boolean isTurno(){
        return nombreJugador.equals(jugadorActual);
    }

    public ArrayList<String> obtenerCartas(){
        return cartas;
    }

    public void actualizarMano(){
        cartas = this.modelo.obtenerCartas(nombreJugador);
        vista.mostrarCartas(cartas);
    }
    public void descartar(int opcion){
        modelo.descartar(nombreJugador,opcion);
    }

    public void terminoTurno(){
        modelo.cambiarTurno(idJA);
    }

    public void pasoTurno(){
        modelo.terminarTurno(nombreJugador);
    }

    public void tomarCarta(){
        modelo.tomarCarta(nombreJugador);
    }

    public void tirarCarta(String jugadorDestino,Integer idCarta,int idOrgano){
        modelo.tirarCarta(jugadorActual,jugadorDestino,idCarta,idOrgano);
    }

    public ArrayList<String> obtenerOrganos(String jugadorDestino){
        return modelo.obtenerOrganos(jugadorDestino);
    }
    public void terminoPartida(){
        modelo.hayGandor();
    }
    public String abandonoPartida(){
        modelo.abandonoPartida();
        return nombreJugador;
    }


}
