package padre.virus.gameController;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import padre.virus.Save;
import padre.virus.modelo.*;
import padre.virus.vistas.IVista;
import padre.virus.vistas.VistaGrafica.VistaGrafica;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements IControladorRemoto {

    private IModelo modelo;
    private IVista vista;
    private IJugador nombreJugador;
    IJugador jugadorActual;
    int idJA;
    String Ganador;
    private boolean partidaIniciada = false;
    private ArrayList<ICarta> mazo;
    private ArrayList<IJugador> jugadores;
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
                    this.vista.mostrarTexto("El Jugador "+jugadores.get(jugadores.size() - 1 ).getNombre()+" se ha unido a la partida\n");
                }
                case PARTIDA_INICIADA -> {
                    partidaIniciada = true;
                    cartas = this.modelo.obtenerCartas(nombreJugador.getNombre());
                    organos = this.modelo.obtenerOrganos(nombreJugador.getNombre());
                    jugadorActual = this.modelo.turnoActual();
                    mazo = this.modelo.obtenerMazo();
                    idJA = 0;
                    this.vista.cartelInicioPartida();
                    this.vista.mostarInicioPartido(jugadorActual,cartas,organos);
                }
                case ROBO_CARTA -> {
                    cartas = this.modelo.obtenerCartas(nombreJugador.getNombre());
                }
                case TERMINO_TURNO -> {
                    if(Ganador==null){
                        cartas = this.modelo.obtenerCartas(nombreJugador.getNombre());
                        if(jugadorActual.getNombre().equals(nombreJugador.getNombre())){
                            //vista.mostrarTurno(jugadorActual);
                            this.modelo.tomarCarta(nombreJugador);
                            vista.mostrarCuerposEnLista(nombreJugador,jugadores);
                            vista.mostrarCuerpo(this.modelo.obtenerOrganos(nombreJugador.getNombre()));
                            vista.mostrarCartas(cartas);
                            vista.terminarTurno();
                        }
                        jugadorActual = this.modelo.turnoActual();
                        vista.notificarMensaje("Es turno del jugador: " + jugadorActual.getNombre());
                        if(jugadorActual.getNombre().equals(nombreJugador.getNombre())){
                            vista.mostrarTurno(jugadorActual);
                            this.modelo.tomarCarta(nombreJugador);
                            vista.mostrarCuerposEnLista(nombreJugador,jugadores);
                            vista.mostrarCuerpo(this.modelo.obtenerOrganos(nombreJugador.getNombre()));
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

                }
                case PARTIDA_FINALIZADA -> {
                    Ganador = this.modelo.getGanador();
                    vista.partidaTerminada(Ganador);

                }
                case ABANDONO_PARTIDA -> {
                    vista.abandonoPartida(nombreJugador);
                }
                case ACTUALIZAR_MAZOS -> {
                    vista.actualizarMazo(obtenerMazo(),obtenerMazoDescarte());
                }
                case MENSAJE_CHAT -> {
                    vista.mostrarChat(this.modelo.getMensaje(), this.modelo.getJug());
                }

            }
        }
    }

    public ArrayList<IJugador> listaJugadores(){
        return jugadores;
    }

    public ArrayList<IJugador> listaIJugadores(){
        try {
            return modelo.obtenerJugadores();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerMazo() {

        try {
            return modelo.obtenerMazo().size();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public int obtenerMazoDescarte() {

        try {
            return modelo.obtenerMazoDescarte().size();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void AgregarJugador(String nombre){
        try {
            nombreJugador = modelo.agregarJugador(nombre);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public int getIdJA(){return nombreJugador.getID();}

    public String getNombre() {
        return nombreJugador.getNombre();
    }
    public IJugador getJugador(){
        return nombreJugador;
    }

    public IJugador getJugadorActual(){
        return jugadorActual;
    }

    public boolean isPartidaIniciada(){
        return partidaIniciada;
    }

    public boolean isTurno(){
        return nombreJugador.getNombre().equals(jugadorActual.getNombre());
    }

    public ArrayList<ICarta> obtenerCartas(){
        return cartas;
    }
    public boolean esCura(int indice){
        boolean cura = false;
        for(ICarta carta : obtenerCartas()){
            if(carta instanceof Cura){
                cura = true;
            }
        }
        return cura;
    }

    public String getJugadorPorID(int id){
        try {
            return modelo.getOponentePorID(id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ICarta> getManoContrincante(String nombre) {
        try {
            return modelo.obtenerCartas(nombre);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOponenteLBL(){
        try{
            return modelo.getOponenteLBL(nombreJugador.getID());
        } catch (RemoteException e){
            throw new RuntimeException(e);
        }

    }

    public void actualizarMano(){
        try {
            cartas = this.modelo.obtenerCartas(nombreJugador.getNombre());
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

    public void pasoTurno(){
        try {
            modelo.terminarTurno(nombreJugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void tirarCarta(String jugadorDestino,Integer idCarta,int idOrgano){
        try {
            modelo.tirarCarta(jugadorActual.getNombre(),jugadorDestino,idCarta,idOrgano);
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
    public boolean terminoPartida(){
        try {
            return modelo.hayGandor();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGanador(){
        try {
            return modelo.getGanador();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public IJugador abandonoPartida(){
        try {
            modelo.abandonoPartida();
            return nombreJugador;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    /*public void reiniciar(){
        try {
            modelo.reiniciar();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

     */

    public void actualizarChat(String txt, String jugador){
        try {
            for(IJugador j: jugadores){
                if(jugador.equals(j.getNombre())){

                    modelo.mostrarChat(txt,j);
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarPartida(String nombreSave){
        try {
            modelo.guardarPartida(nombreSave);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarPartida(int idSave){
        try {
            modelo.cargarPartida(idSave,nombreJugador.getNombre());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getPartidasGuardadas(){
        ArrayList<String> partidas;
        try {
            partidas = modelo.getListaPartidas();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return partidas;
    }


    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IModelo) modeloRemoto;
    }


}
