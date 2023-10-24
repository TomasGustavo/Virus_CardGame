package padre.virus.juego;


import java.awt.*;

public class Carta {
    private Color color;
    private Tipo tipo;
    private Image imagen;


    public Carta (Color color, Tipo tipo){
        this.color = color;
        this.tipo = tipo;
    }
    public Color getColor(){
        return color;
    }
    public Tipo getTipo(){
        return tipo;
    }

    @Override
    public String toString(){
        return "Carta [color= "+color+", tipo= "+tipo+"]";
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }
    /*
    public Image getImagen(){
        String color = getColor().toString().toLowerCase();
        String tipo = getTipo().toString().toLowerCase();

        imagen = new Image ("/image/carta/"+color+tipo+".png");
        return imagen;
    }*/

    public void setImagen(Image imagen){
        this.imagen = imagen;
    }
}
