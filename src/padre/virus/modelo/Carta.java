package padre.virus.modelo;

import java.io.Serializable;

public abstract class Carta implements ICarta, Serializable {

    protected Color color;
    protected Tipo tipo;

    public Carta (Color color,Tipo tipo){
        this.color = color;
        this.tipo = tipo;
    }


    public Color getColor(){
        return color;
    }
    public Tipo getTipo(){
        return tipo;
    }
    public String toString(){
        return color.toString()+" "+ tipo;
    }

}
