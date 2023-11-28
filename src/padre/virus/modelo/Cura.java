package padre.virus.modelo;

public class Cura extends Carta{

    public Cura(Color color){
        super (color,Tipo.CURA);
    }

    public void cura(Organo organo){
        if(organo.estaSano() && (organo.getColor() == this.getColor()) && organo.getCount() ==1){
            organo.setInmune(true);
        }else if(organo.estaSano() && (organo.getColor() == this.getColor()) && organo.getCount() == 0){
            organo.setSano(true);
            organo.setCount(1);
        }
    }
}
