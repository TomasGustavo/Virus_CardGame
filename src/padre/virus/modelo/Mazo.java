package padre.virus.modelo;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class Mazo implements Serializable{

    private ArrayList<Carta> mazo;

    public Mazo (boolean vacio){
        this.mazo = new ArrayList<>();
        if(!vacio){
            buildMazo();
        }

    }

    public ArrayList<Carta> getMazo(){
        return mazo;
    }

    public void add (Carta carta){
        mazo.add(carta);
    }

    public ArrayList<ICarta> obtenerMazo(){
        ArrayList<ICarta> cartas = new ArrayList<>();
        for(Carta carta : mazo){
            cartas.add(carta);
        }
        return cartas;
    }

    public void vaciarMazo(){
        while(mazo.size() != 0){
            mazo.remove(0);
        }
    }

    public Carta contruirCarta(Tipo tipo, Color color){
        switch (tipo){
            case CURA: return new Cura(color);
            case ORGANO: return new Organo(color);
            case VIRUS: return new Virus(color);
            default: throw new IllegalArgumentException("Tipo de carta invalido");
        }
    }

    public void addToMazo(int count, Tipo tipo,Color color){
        for(int i =0;i<count;i++){
            mazo.add(contruirCarta(tipo,color));
        }
    }

    public void buildMazo(){
        for(Color color : Arrays.asList(Color.ROJO,Color.AMARILLO,Color.AZUL,Color.VERDE)){
            addToMazo(5,Tipo.ORGANO,color);//5
            addToMazo(5,Tipo.VIRUS,color);//4
            addToMazo(4,Tipo.CURA,color);//4
        }
        addToMazo(3,Tipo.ORGANO,Color.MULTICOLOR);//1
        addToMazo(3,Tipo.VIRUS,Color.MULTICOLOR);//1
        addToMazo(4,Tipo.CURA,Color.MULTICOLOR);//4
    }

    public void BarajarMazo(){
        Collections.shuffle(this.getMazo());
    }

    public Carta TomarCarta(){
        return this.getMazo().remove(0);
    }

}
