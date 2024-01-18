package padre.virus.modelo;

import padre.virus.observer.Observable;
import padre.virus.observer.Observador;

import java.util.ArrayList;
import java.util.Iterator;

public class Modelo implements Observable{

    private ArrayList<Observador> observadores;
    private Mazo mazo;
    private final ArrayList<Jugador> jugadores;

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

    public void agregarJugador(String nombre){
        if(jugadores.size() < 6){
            Jugador jugador = new Jugador(nombre);
            jugadores.add(jugador);
            this.notificar(Eventos.NUEVO_JUGADOR);
        }
    }

    public void jugar(){
        if(jugadores.size() >=2){
            this.mazo = new Mazo();
            mazo.BarajarMazo();
            RepartirCartas();
            asignarTurno();
            this.notificar(Eventos.PARTIDA_INICIADA);
        }
    }

    public void asignarTurno(){
        jugadores.get(0).setSuTurno(true);
    }

    public String cambiarTurno (int jugadorID){
        int indice = (jugadorID + 1) % jugadores.size();
        String jActual = turnoActual();
        Jugador jugador = jugadores.get(indice);
        jugador.setSuTurno(true);

        return jugador.getNombre();
    }

    public String turnoActual(){
        for(Jugador jugador : jugadores){
            if(jugador.isTurno()){
                return jugador.getNombre();
            }
        }
        return null;
    }


    public void RepartirCartas(){
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
    public void tomarCarta(String nombreJugador){
        for(Jugador i : jugadores){
            if(i.getNombre().equals(nombreJugador)){
                while(i.getMano().size() < 3){
                    Carta carta = mazo.TomarCarta();
                    if(carta instanceof Organo){
                        i.setCuerpo((Organo) carta);
                    } else{
                        i.tomarCarta(carta);
                    }
                }
            }
        }
    }

    public ArrayList<String> obtenerJugadores(){
        ArrayList<String> ListaJugadores = new ArrayList<>();
        for(Jugador jugador : jugadores){
            ListaJugadores.add(jugador.getNombre());
        }
        return ListaJugadores;
    }

    public void tirarCarta(String jugadorOrigen, String jugadorDestino, int IdCarta,int IdOrgano){
        Jugador jugadororigen = obtenerJugadorPorNombre(jugadorOrigen);
        Jugador jugadordestino = obtenerJugadorPorNombre(jugadorDestino);

        if(jugadorOrigen!= null && jugadordestino!= null){
            Carta carta = jugadororigen.getMano().get(IdCarta);

            if(carta != null){
                if(carta instanceof Cura){
                    tirarCura(carta,jugadordestino.getCuerpo().get(IdOrgano));
                } else if(carta instanceof Virus){
                    tirarVirus(carta,jugadordestino.getCuerpo().get(IdOrgano));
                }

                jugadororigen.descartar(IdCarta);

                notificar(Eventos.TERMINO_TURNO);
            }
        }
    }

    private Jugador obtenerJugadorPorNombre(String nombre){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }

    public void tirarCura(Carta carta,Organo organo){

        ((Cura) carta).cura(organo);
    }
    public boolean tirarVirus(Carta carta, Organo organo){
        return ((Virus) carta).Infectado(organo);
    }
    public void tirarTratamiento(Carta carta,Jugador jugador){

    }

    public ArrayList<String> obtenerMazo(){
        return mazo.obtenerMazo();
    }

    public ArrayList<String> obtenerCartas (String nombre){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.obtenerCartas();
            }
        }
        return null;
    }

    public ArrayList<String> obtenerOrganos(String nombre){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.obtenerCuerpo();
            }
        }
        return null;
    }

    public String getGanador(){
        return ganador;
    }
    @Override
    public void notificar(Object evento) {
        for(Observador observador: observadores){
            observador.actualizar(evento,this);
        }

    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observador observador) {
        Iterator<Observador> iterador = observadores.iterator();
        while (iterador.hasNext()){
            Observador observadorActual = iterador.next();
            if(observadorActual.equals(observador)){
                iterador.remove();
            }
        }

    }

    public void descartar(String nombreJugador, int opcion){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador)){
                jugador.descartar(opcion);
                jugador.tomarCarta(mazo.TomarCarta());
                notificar(Eventos.TERMINO_TURNO);
            }
        }
    }
    public void terminarTurno(String nombreJugador){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombreJugador)){
                notificar(Eventos.TERMINO_TURNO);
            }
        }
    }

    public void partidaTerminada(String ganador){
        this.ganador = ganador;
        notificar(Eventos.PARTIDA_FINALIZADA);
    }
}
