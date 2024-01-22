package padre.virus.modelo;

public class Virus extends Carta{

    public Virus(Color color){
        super(color,Tipo.VIRUS);
    }

    public boolean Infectado(Organo organo){
        boolean infectado = false;
        if(!organo.esInmune() && ((organo.getColor() == this.getColor())  || this.getColor() == Color.MULTICOLOR || organo.getColor() == Color.MULTICOLOR) && organo.estaSano()){
            if(organo.getCount() == 1){
                organo.setCount(0);
            }
            organo.setSano(false);
            infectado = true;

        }
        return infectado;
    }
}
