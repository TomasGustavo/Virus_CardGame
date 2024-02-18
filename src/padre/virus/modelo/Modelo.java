package padre.virus.modelo;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import padre.virus.observer.Observador;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Modelo extends ObservableRemoto implements IModelo {

    private ArrayList<Observador> observadores;
    private Mazo mazo;
    private Mazo mazoDescarte;
    private final ArrayList<IJugador> jugadores;
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
    public IJugador agregarJugador(String nombre) throws RemoteException {

        IJugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
        this.notificarObservadores(Eventos.NUEVO_JUGADOR);
        return jugador;


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
    public IJugador cambiarTurno(int jugadorID) throws RemoteException{
        int indice = (jugadorID + 1) % jugadores.size();
        //String jActual = turnoActual();
        IJugador jugador = jugadores.get(indice);
        jugador.setSuTurno(true);
        return jugador;
    }

    @Override
    public void hayGandor() throws RemoteException{
        for(IJugador i : jugadores){
            if(obtenerOrganos(i.getNombre()).size() == 4 && i.organosSanos()){
                partidaTerminada(i.getNombre());
            }
        }
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
        for(IJugador jugador : jugadores){
            ListaJugadores.add(jugador.getNombre());
        }
        return ListaJugadores;
    } //TODO cambiar TODO a Ijugadores porque si no es un dolor de pija.

    public ArrayList<IJugador> obtenerIJugadores() throws RemoteException{
        ArrayList<IJugador> ListaJugadores = new ArrayList<>();
        for(IJugador jugador : jugadores){
            ListaJugadores.add(jugador);
        }
        return ListaJugadores;
    } //TODO despues borrar esto

    @Override
    public void tirarCarta(String jugadorOrigen, String jugadorDestino, Integer IdCarta, int IdOrgano) throws RemoteException{
        IJugador jugadororigen = obtenerJugadorPorNombre(jugadorOrigen);
        IJugador jugadordestino = obtenerJugadorPorNombre(jugadorDestino);
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
                //notificar(Eventos.TERMINO_TURNO);
            }
        }
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
