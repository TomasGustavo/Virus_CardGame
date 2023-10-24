package padre.virus.juego;

public class Virus extends Carta{

    public Virus(Color color){
        super(color,Tipo.VIRUS);
    }

    public boolean Infectado(Organo organo){
        boolean infectado = false;
        if(!organo.esInmune() && organo.estaSano()){
            if(organo.getCount() == 1){
                organo.setCount(0);
            }else{
                organo.setSano(false);
                infectado = true;
            }
        }
        return infectado;
    }
}
