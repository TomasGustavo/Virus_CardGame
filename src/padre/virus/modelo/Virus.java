package padre.virus.modelo;

public class Virus extends Carta {

    public Virus(Color color){
        super(color,Tipo.VIRUS);
    }

    private boolean Infectado(Organo organo){
        boolean infectado = false;
        if(!organo.esInmune() && ((organo.getColor() == this.getColor())  || this.getColor() == Color.MULTICOLOR || organo.getColor() == Color.MULTICOLOR)){
            if(organo.getCountCura() == 1){
                organo.setCountCura(0);
            }
            organo.setSano(false);
            organo.setCountVirus(organo.getCountVirus()+1);
            infectado = true;
        }
        return infectado;
    }

    @Override
    public boolean accion(Organo organo) {
        return Infectado(organo);
    }

    @Override
    public boolean estaSano() {
        return false;
    }

    @Override
    public boolean esInmune() {
        return false;
    }
}
