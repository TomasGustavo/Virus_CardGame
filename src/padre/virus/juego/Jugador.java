package padre.virus.juego;

import java.util.ArrayList;

public class Jugador {
    private ArrayList<Organo> cuerpo;
    private ArrayList<Carta> mano;

    public Jugador(){
        this.cuerpo = new ArrayList<>();
        this.mano = new ArrayList<>();
    }

    public ArrayList<Organo> getCuerpo(){
        return cuerpo;
    }
    public void setCuerpo(Organo organo){
        // SI EL CUERPO ESTA VACIO AÑADE EL ORGNAO
        if(this.cuerpo == null){
            this.cuerpo.add(organo);
        }else{
            // VERIFICA SI EL ORGANO A AGREGAR ES DE OTRO COLOR DE CUALQUIERA QUE YA HAYA EN EL CUERPO
            for(int i =0;i<this.cuerpo.size();i++){
                if(!this.cuerpo.get(i).getColor().equals(organo.getColor())){
                    this.cuerpo.add(organo);
                }
            }
        }
    }

    public ArrayList<Carta> getMano(){
        return mano;
    }

    public void setMano(ArrayList<Carta> mano){
        this.mano = mano;
    }
}
