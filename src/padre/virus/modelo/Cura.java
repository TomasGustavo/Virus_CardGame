package padre.virus.modelo;

public class Cura extends Carta {

    public Cura(Color color){
        super (color,Tipo.CURA);
    }

    private boolean cura(Organo organo){
        boolean curado = false;
        if(((organo.getColor() == this.getColor()) || organo.getColor() == Color.MULTICOLOR || this.getColor() == Color.MULTICOLOR)){
            organo.setCountVirus(0);
            if(organo.getCountCura() == 1){
                organo.setInmune(true);
            }else {
                organo.setSano(true);
                organo.setCountCura(1);
            }
            curado = true;
        }
        return curado;
    }

    @Override
    public boolean accion(Organo organo) {
        return cura(organo);
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
