package padre.virus.modelo;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import padre.virus.observer.Observable;
import padre.virus.observer.Observador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Modelo extends ObservableRemoto implements IModelo {

    private ArrayList<Observador> observadores;
    private Mazo mazo;
    private Mazo mazoDescarte;
    private final ArrayList<Jugador> jugadores;
    private String mensaje;
    private String jug;

    String ganador;


    public Modelo(){
        this.jugadores = new ArrayList<>();
        this.observadores = new ArrayList<>();

    }

/*
    public String getMensajeSistema(){
        return mensajeSistema;
    }
*/

    @Override
    public void agregarJugador(String nombre) throws RemoteException {
        if(jugadores.size() < 6){
            Jugador jugador = new Jugador(nombre);
            jugadores.add(jugador);
            this.notificarObservadores(Eventos.NUEVO_JUGADOR);
        }
    }

    @Override
    public void jugar() throws RemoteException{
        if(jugadores.size() >=2){
            this.mazo = new Mazo(false);
            this.mazoDescarte = new Mazo(true);
            mazo.BarajarMazo();
            RepartirCartas();
            asignarTurno();
            this.notificarObservadores(Eventos.PARTIDA_INICIADA);
        }
    }

    private void asignarTurno(){
        jugadores.get(0).setSuTurno(true);
    }

    @Override
    public String cambiarTurno(int jugadorID) throws RemoteException{
        int indice = (jugadorID + 1) % jugadores.size();
        String jActual = turnoActual();
        Jugador jugador = jugadores.get(indice);
        jugador.setSuTurno(true);
        return jugador.getNombre();
    }

    @Override
    public void hayGandor() throws RemoteException{
        for(Jugador i : jugadores){
            if(obtenerOrganos(i.getNombre()).size() == 4 && i.organosSanos()){
                partidaTerminada(i.getNombre());
            }
        }
    }
    @Override
    public String turnoActual() throws RemoteException{
        for(Jugador jugador : jugadores){
            if(jugador.isTurno()){
                return jugador.getNombre();
            }
        }
        return null;
    }


    private void RepartirCartas(){
        mazo.BarajarMazo();
        for (Jugador i : jugadores){
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
    public void tomarCarta(String nombreJugador) throws RemoteException{
        int j;
        if(mazo.obtenerMazo().isEmpty()){
            for(Carta carta : mazoDescarte.getMazo()){
                this.mazo.add(carta);
            }
            mazoDescarte.vaciarMazo();
        }
        for(Jugador i : jugadores){
            if(i.getNombre().equals(nombreJugador)){
                j = i.getMano().size();
                while(i.getMano().size() < 3 && j < 3){
                    Carta carta = mazo.TomarCarta();
                    if(carta instanceof Organo){
                        //System.out.println("paso tomarCarta..."+nombreJugador+"\n");
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
    }

    @Override
    public ArrayList<String> obtenerJugadores() throws RemoteException{
        ArrayList<String> ListaJugadores = new ArrayList<>();
        for(Jugador jugador : jugadores){
            ListaJugadores.add(jugador.getNombre());
        }
        return ListaJugadores;
    }

    @Override
    public void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException{
        Jugador jugadororigen = obtenerJugadorPorNombre(jugadorOrigen);
        Jugador jugadordestino = obtenerJugadorPorNombre(jugadorDestino);
        if(jugadorOrigen != null && jugadordestino != null){
            boolean jugada = true;
            try {
                Carta carta = jugadororigen.getMano().get(IdCarta-1);
                if(carta != null){
                    if(carta instanceof Cura){
                        Organo organoVictima = jugadordestino.getCuerpo().remove(IdOrgano-1);
                        jugada = ((Cura) carta).cura(organoVictima);
                        jugadordestino.setCuerpo(organoVictima);
                    } else if(carta instanceof Virus){
                        Organo organoVictima = jugadordestino.getCuerpo().remove(IdOrgano-1);
                        jugada = ((Virus) carta).Infectado(organoVictima);
                        jugadordestino.setCuerpo(organoVictima);
                    }
                    if(jugada){
                        jugadororigen.descartar(IdCarta-1);
                    }
                }
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        notificarObservadores(Eventos.TERMINO_TURNO);
    }

    private Jugador obtenerJugadorPorNombre(String nombre){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }

    //public void tirarTratamiento(Carta carta,Jugador jugador){}

    @Override
    public ArrayList<String> obtenerMazo()throws RemoteException{
        return mazo.obtenerMazo();
    }

    @Override
    public ArrayList<String> obtenerCartas(String nombre)throws RemoteException{
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.obtenerCartas();
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> obtenerOrganos(String nombre)throws RemoteException{
        for(Jugador jugador : jugadores){
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
    public void descartar(String nombreJugador, int opcion)throws RemoteException{
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador)){
                actualizarMazoDescarte(jugador.getMano().get(opcion));
                jugador.descartar(opcion);
                tomarCarta(nombreJugador);
                //notificar(Eventos.TERMINO_TURNO);
            }
        }
    }

    private void actualizarMazoDescarte(Carta carta){
        mazoDescarte.add(carta);
    }
    @Override
    public void terminarTurno(String nombreJugador)throws RemoteException{
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador)){
                notificarObservadores(Eventos.TERMINO_TURNO);
            }
        }
    }

    @Override
    public void partidaTerminada(String ganador)throws RemoteException{
        this.ganador = ganador;
        //if(turnoActual().equals(ganador)){
        notificarObservadores(Eventos.PARTIDA_FINALIZADA);
        //}

    }

    public void mostrarChat(String txt,String jugador){
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
    public String getJug(){
        return jug;
    }
}
