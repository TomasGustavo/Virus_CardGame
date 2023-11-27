package padre.virus.modelo;

public class Organo extends Carta {

    private boolean sano;
    private boolean inmune;
    private int count;

    public Organo (Color color){
        super(color,Tipo.ORGANO);
        this.sano = true;
        this.inmune = false;
        this.count = 0;
    }

    public boolean estaSano(){
        return sano;
    }
    public void setSano(boolean sano){
        this.sano = sano;
    }
    public boolean esInmune(){
        return inmune;
    }
    public void setInmune(boolean inmune){
        this.inmune = inmune;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }

    public String toString(){
        if(estaSano()){
            if(esInmune()){
                return "Organo "+this.getColor()+" [INMUNE]";
            }
            return "Organo "+this.getColor()+" [SANO]";
        }
        return "Organo " + this.getColor() + " [INFECTADO]";
    }
}
