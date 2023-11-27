package padre.virus.modelo;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Organo> cuerpo;
    private ArrayList<Carta> mano;
    private Boolean SuTurno;

    private static int ID = 0;
    private int IdJugador;



    public Jugador(String nombre){
        this.nombre = nombre;
        this.cuerpo = new ArrayList<>();
        this.mano = new ArrayList<>();
        this.IdJugador = Jugador.ID++;
    }

    public ArrayList<Organo> getCuerpo(){
        return cuerpo;
    }
    public void setCuerpo(Organo organo){
        // SI EL CUERPO ESTA VACIO AÑADE EL ORGNAO
        if(this.cuerpo == null){
            this.cuerpo.add(organo);
        }else if(this.cuerpo.size() < 4){
            boolean bandera = false;
            // VERIFICA SI EL ORGANO A AGREGAR ES DE OTRO COLOR DE CUALQUIERA QUE YA HAYA EN EL CUERPO
            for(int i =0;i<this.cuerpo.size();i++){
                if(this.cuerpo.get(i).getColor().equals(organo.getColor())){
                    bandera = true;
                }
            }
            if (!bandera){
                this.cuerpo.add(organo);
            }
        }
    }

    public void setSuTurno(boolean turno){
        this.SuTurno = turno;
    }

    public boolean isTurno(){
        return SuTurno;
    }
    public int CantCartasMano(){
        return mano.size();
    }
    public String getNombre(){
        return nombre;
    }
    public ArrayList<Carta> getMano(){
        return mano;
    }
    public ArrayList<String> obtenerCartas(){
        ArrayList<String> cartas = new ArrayList<>();
        for(Carta carta : mano){
            cartas.add(carta.toString());
        }
        return cartas;
    }
    public ArrayList<String> obtenerCuerpo(){
        ArrayList<String> organos = new ArrayList<>();
        for(Organo organo : cuerpo){
            organos.add(organo.toString());
        }
        return organos;
    }

    public void setMano(ArrayList<Carta> mano){
        this.mano = mano;
    }

    public void tomarCarta(Carta carta){
        mano.add(carta);
    }

    public void descartar(int carta){
        mano.remove(carta);
    }
}
