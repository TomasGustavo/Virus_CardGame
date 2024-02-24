package padre.virus.modelo;

public class Organo extends Carta {

    private boolean sano;
    private boolean inmune;
    private int countCura;
    private int countVirus;

    public Organo (Color color){
        super(color,Tipo.ORGANO);
        this.sano = true;
        this.inmune = false;
        this.countCura = 0;
        this.countVirus = 0;
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
    public int getCountCura(){
        return countCura;
    }
    public void setCountCura(int count){
        this.countCura = count;
    }
    public int getCountVirus(){
        return countVirus;
    }
    public void setCountVirus(int count){
        this.countVirus = count;
    }
    public void extripado(){
        setSano(true);
        setCountVirus(0);
        setCountCura(0);
    }

    public String toString(){
        if(estaSano()){
            if(esInmune()){
                return "Organo "+"["+this.getColor()+"]"+" [INMUNE]";
            }
            return "Organo "+"["+this.getColor()+"]"+" [SANO]";
        }
        return "Organo " + "["+this.getColor()+"]" +" [INFECTADO]";
    }

}
