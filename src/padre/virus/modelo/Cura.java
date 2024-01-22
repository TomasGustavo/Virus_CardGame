package padre.virus.modelo;

public class Cura extends Carta{

    public Cura(Color color){
        super (color,Tipo.CURA);
    }

    public boolean cura(Organo organo){
        boolean curado = true;
        if(organo.estaSano()){
            if(((organo.getColor() == this.getColor()) || organo.getColor() == Color.MULTICOLOR || this.getColor() == Color.MULTICOLOR) && organo.getCount() ==1){
                organo.setInmune(true);
            }
            curado = false;
        } else{
            organo.setSano(true);
            organo.setCount(1);
        }
        return curado;
    }
}
