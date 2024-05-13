package padre.virus.modelo;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import padre.virus.Save;
import padre.virus.observer.Observador;
import padre.virus.serializacion.Serializador;

import javax.security.sasl.SaslClient;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Modelo extends ObservableRemoto implements IModelo,Serializable {

    private static Modelo instancia;
    private ArrayList<Observador> observadores;
    private Mazo mazo;
    private Mazo mazoDescarte;
    private ArrayList<IJugador> jugadores;
    private String mensaje;
    private IJugador jug;
    private static Serializador serializador;

    String ganador;
    private boolean partidaCargada = false;
    private boolean partidaIniciada= false;


    public Modelo(){
        this.jugadores = new ArrayList<>();
        this.observadores = new ArrayList<>();

    }

    public static IModelo getInstancia() {

        if (instancia == null){
            instancia = new Modelo();
        }
        return instancia;
    }


    @Override
    public IJugador agregarJugador(String nombre) throws RemoteException {

        IJugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
        if(jugadores.size() ==1){
            jugador.setEsHost(true);
        }else{
            jugador.setEsHost(false);
        }
        this.notificarObservadores(Eventos.NUEVO_JUGADOR);
        return jugador;
    }

    public boolean esHost(IJugador jugador) throws RemoteException{
        return jugador.getEsHost();
    }

    public void reiniciar(){
        ganador = "";
        this.mazo.vaciarMazo();
        for(IJugador jugador: jugadores){
            jugador.vaciarJugador();
        }
    }

    @Override
    public void jugar() throws RemoteException{
        if(!partidaCargada){
            if(partidaIniciada){
                reiniciar();
            }
            if(jugadores.size() >=2){
                this.mazo = new Mazo(false);
                this.mazoDescarte = new Mazo(true);
                mazo.BarajarMazo();
                RepartirCartas();
                asignarTurno();
                partidaIniciada=true;
                this.notificarObservadores(Eventos.PARTIDA_INICIADA);
            }
        }else{
            partidaCargada = false;
            notificarObservadores(Eventos.PARTIDA_INICIADA);
        }
    }

    private void asignarTurno(){
        jugadores.get(0).setSuTurno(true);
    }

    @Override
    public IJugador cambiarTurno(int jugadorID) throws RemoteException{
        if(hayGandor()){
            notificarObservadores(Eventos.PARTIDA_FINALIZADA);
        }
        int indice = (jugadorID + 1) % jugadores.size();
        //String jActual = turnoActual();
        IJugador jugador = jugadores.get(indice);
        jugador.setSuTurno(true);
        return jugador;
    }

    @Override
    public boolean hayGandor() throws RemoteException{
        for(IJugador i : jugadores){
            if(obtenerOrganos(i.getNombre()).size() == 4 && i.organosSanos()){
                partidaTerminada(i.getNombre());
                return true;
            }
        }
        return false;
    }
    @Override
    public IJugador turnoActual() throws RemoteException{
        for(IJugador jugador : jugadores){
            if(jugador.isTurno()){
                return jugador;
            }
        }
        return null;
    }


    private void RepartirCartas(){
        mazo.BarajarMazo();
        for (IJugador i : jugadores){
            for(int j = 0; j<3 ; j++){
                Carta carta = mazo.TomarCarta();
                if(carta instanceof Organo){
                    i.setCuerpo((Organo) carta);
                } else{
                    i.tomarCarta(carta);
                }
            }
        }
    }
    @Override
    public void tomarCarta(IJugador nombreJugador) throws RemoteException{
        int j;
        if(mazo.obtenerMazo().isEmpty()){
            for(Carta carta : mazoDescarte.getMazo()){
                this.mazo.add(carta);
            }
            mazoDescarte.vaciarMazo();
        }
        for(IJugador i : jugadores){
            if(i.getNombre().equals(nombreJugador.getNombre())){
                j = i.getMano().size();
                while(i.getMano().size() < 3 && j < 3){
                    Carta carta = mazo.TomarCarta();
                    if(carta instanceof Organo){
                        if(!i.setCuerpo(carta)){
                            i.tomarCarta(carta);
                        }
                    } else{
                        i.tomarCarta(carta);
                    }
                    j++;
                }
            }
        }
        notificarObservadores(Eventos.ACTUALIZAR_MAZOS);
    }

    @Override
    public ArrayList<IJugador> obtenerJugadores() throws RemoteException{
        ArrayList<IJugador> ListaJugadores = new ArrayList<>();
        for(IJugador jugador : jugadores){
            ListaJugadores.add(jugador);
        }
        return ListaJugadores;
    }


    @Override
    public void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException{
        IJugador jugadororigen = obtenerJugadorPorNombre(jugadorOrigen);
        IJugador jugadordestino = obtenerJugadorPorNombre(jugadorDestino);
        if(jugadorOrigen != null && jugadordestino != null){
            boolean jugada = true;
            try {
                Carta carta = jugadororigen.getMano().get(IdCarta);
                if(carta != null){

                    Organo organoVictima = jugadordestino.getCuerpo().remove(IdOrgano);
                    jugada = carta.accion(organoVictima);

                    if(organoVictima.getCountVirus() < 2){
                        jugadordestino.setCuerpo(organoVictima);
                    }else if (organoVictima.getCountVirus()>=2){
                        organoVictima.extripado();
                        actualizarMazoDescarte(organoVictima);
                    }

                    if(jugada){
                        jugadororigen.descartar(IdCarta);
                    }
                }
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        jugadororigen.setSuTurno(false);
        cambiarTurno(jugadororigen.getID());
        notificarObservadores(Eventos.TERMINO_TURNO);
    }

    private IJugador obtenerJugadorPorNombre(String nombre){
        for(IJugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }

    public String getOponente(int jugadorID) throws RemoteException{
        int id = (jugadorID+1)%jugadores.size();
        return jugadores.get(id).getNombre();
    }

    @Override
    public ArrayList<ICarta> obtenerMazo()throws RemoteException{
        return mazo.obtenerMazo();
    }
    public ArrayList<ICarta> obtenerMazoDescarte() throws RemoteException{
        return mazoDescarte.obtenerMazo();
    }

    @Override
    public ArrayList<ICarta> obtenerCartas(String nombre)throws RemoteException{
        for(IJugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.obtenerCartas();
            }
        }
        return null;
    }

    @Override
    public ArrayList<ICarta> obtenerOrganos(String nombre)throws RemoteException{
        for(IJugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.obtenerCuerpo();
            }
        }
        return null;
    }

    @Override
    public String getGanador()throws RemoteException{
        return ganador;
    }

    @Override
    public void abandonoPartida()throws RemoteException{
        this.notificarObservadores(Eventos.ABANDONO_PARTIDA);
    }


    @Override
    public void descartar(IJugador nombreJugador, int opcion)throws RemoteException{
        for(IJugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador.getNombre())){
                actualizarMazoDescarte(jugador.getMano().get(opcion));
                jugador.descartar(opcion);
                tomarCarta(nombreJugador);
            }
        }
        notificarObservadores(Eventos.ACTUALIZAR_MAZOS);
    }

    private void actualizarMazoDescarte(Carta carta){
        mazoDescarte.add(carta);
    }
    @Override
    public void terminarTurno(IJugador nombreJugador)throws RemoteException{
        for(IJugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador.getNombre())){
                jugador.setSuTurno(false);
                cambiarTurno(jugador.getID());
                notificarObservadores(Eventos.TERMINO_TURNO);
            }
        }
    }


    @Override
    public void partidaTerminada(String ganador)throws RemoteException{
        this.ganador = ganador;

    }

    public void mostrarChat(String txt,IJugador jugador){
        try {
            this.mensaje = txt;
            this.jug = jugador;
            notificarObservadores(Eventos.MENSAJE_CHAT);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public String getMensaje(){
        return mensaje;
    }
    public IJugador getJug(){
        return jug;
    }

    // METODOS RELACIONADOS AL GUARDADO Y CARGA DE PARTIDAS

    public void guardarPartida(String nombrePartida)throws RemoteException{
        ArrayList<Save> saves = getPartidasGuardadas();
        if(saves.size()<4){
            Save partida = new Save(nombrePartida,this,jugadores.get(0).getNombre());
            saves.add(partida);
            agregarPartidaGuardada(saves);
        }
    }

    private ArrayList<Save> getPartidasGuardadas(){
        ArrayList<Save> saves = new ArrayList<>();
        serializador = new Serializador("ArchivosDeGuardado.dat");
        Object[] guardado = serializador.readObjects();
        if(guardado != null){
            for(int i = 0; i < guardado.length; i++){
                saves.add((Save) guardado[i]);
            }
        }
        return saves;
    }

    private void agregarPartidaGuardada(ArrayList<Save> partidas){
        serializador.writeOneObject(partidas.get(0));
        for (int i = 1; i<partidas.size();i++) {
            serializador.addOneObject(partidas.get(i));
        }
    }

    public void cargarPartida(int idPartida,String host)throws RemoteException{
        ArrayList<Save> partidas = getPartidasGuardadas();
        if(partidas.size() >= idPartida){
            Save carga = partidas.get(idPartida);
            Modelo juego = carga.getPartidaGuardada();
            IJugador hostCargado = juego.jugadores.get(0);
            cargarDatos(juego);
            //notificarObservadores(Eventos.PARTIDA_INICIADA);
        }
    }
    private void cargarDatos(Modelo juego) throws RemoteException {
        this.mazo = juego.mazo;
        this.mazoDescarte = juego.mazoDescarte;
        this.jugadores = juego.obtenerJugadores();
        this.partidaCargada = true;

    }

    public ArrayList<String> getListaPartidas() throws RemoteException{
        ArrayList<Save> partidas = getPartidasGuardadas();
        ArrayList<String> listaSaves = new ArrayList<>();
        if(!partidas.isEmpty()){
            for(Save actual : partidas){
                listaSaves.add(actual.toString());
            }
        }
        return listaSaves;
    }

}
