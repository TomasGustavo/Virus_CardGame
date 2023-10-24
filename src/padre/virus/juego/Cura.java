package padre.virus.juego;

public class Cura extends Carta{

    public Cura(Color color){
        super (color,Tipo.CURA);
    }

    public void cura(Organo organo){
        if(organo.estaSano()&& organo.getCount() ==1){
            organo.setInmune(true);
        }else {
            organo.setSano(true);
            organo.setCount(1);
        }
    }
}
