package padre.virus.modelo;

import java.util.*;

public class Mazo {

    private List<Carta> mazo;

    public Mazo (){
        this.mazo = new ArrayList<>();
        buildMazo();
    }

    public List<Carta> getMazo(){
        return mazo;
    }
    public ArrayList<String> obtenerMazo(){
        ArrayList<String> cartas = new ArrayList<>();
        for(Carta carta : mazo){
            cartas.add(carta.toString());
        }
        return cartas;
    }

    public Carta contruirCarta(Tipo tipo,Color color){
        switch (tipo){
            case CURA: return new Cura(color);
            case ORGANO: return new Organo(color);
            case VIRUS: return new Virus(color);
            case TRATAMIENTO: return new Tratamiento(color);
            default: throw new IllegalArgumentException("Tipo de carta invalido");
        }
    }

    public void addToMazo(int count, Tipo tipo,Color color){
        for(int i =0;i<count;i++){
            mazo.add(contruirCarta(tipo,color));
        }
    }

    public void buildMazo(){
        Arrays.asList(Color.ROJO,Color.VERDE,Color.AZUL,Color.AMARILLO)
                .stream()
                .forEach(color -> {
                    addToMazo(5,Tipo.ORGANO,color);
                    addToMazo(4,Tipo.VIRUS,color);
                    addToMazo(4,Tipo.CURA,color);
                });

        addToMazo(1,Tipo.ORGANO,Color.MULTICOLOR);
        addToMazo(1,Tipo.VIRUS,Color.MULTICOLOR);
        addToMazo(4,Tipo.CURA,Color.MULTICOLOR);
        addToMazo(10,Tipo.TRATAMIENTO,Color.NONE);
    }

    public void BarajarMazo(){
        Collections.shuffle(this.getMazo());
    }

    public Carta TomarCarta(){
        return this.getMazo().remove(0);
    }

}
