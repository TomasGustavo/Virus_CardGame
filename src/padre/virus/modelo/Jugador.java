package padre.virus.modelo;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Organo> cuerpo;
    private ArrayList<Carta> mano;
    private Boolean SuTurno;

    private static int ID = 0;
    private int IdJugador;



    public Jugador(String nombre){
        this.nombre = nombre;
        this.cuerpo = new ArrayList<>();
        this.mano = new ArrayList<>();
        this.IdJugador = Jugador.ID++;
    }

    public ArrayList<Organo> getCuerpo(){
        return cuerpo;
    }
   /* public void setCuerpo(Organo organo){
        // SI EL CUERPO ESTA VACIO AÑADE EL ORGNAO
        if(this.cuerpo == null){
            this.cuerpo.add(organo);
        }else if(this.cuerpo.size() < 4){
            boolean bandera = false;
            // VERIFICA SI EL ORGANO A AGREGAR ES DE OTRO COLOR DE CUALQUIERA QUE YA HAYA EN EL CUERPO
            for(int i =0;i<this.cuerpo.size();i++){
                if(this.cuerpo.get(i).getColor().equals(organo.getColor())){
                    bandera = true;
                }
            }
            if (!bandera){
                this.cuerpo.add(organo);
            }
        }
    }*/

    public boolean setCuerpo(Carta organo) {
        // Si el cuerpo está vacío o tiene menos de 4 órganos
        if (this.cuerpo == null || this.cuerpo.size() < 4) {
            // Verifica si el órgano a agregar tiene un color diferente a los órganos existentes
            if (!contieneOrganoConMismoColor(organo.getColor())) {
                // Agrega el órgano al cuerpo
                Organo org = (Organo) organo;
                this.cuerpo.add(org);
                //System.out.println("paso...\n");
                return true;

            }
        }
        return false;
    }

    private boolean contieneOrganoConMismoColor(Color color) {
        if (this.cuerpo != null) {
            for (Organo organoExistente : this.cuerpo) {
                if (organoExistente.getColor().equals(color)) {
                    //System.out.printf("existe orgnao con ese color \n");
                    return true; // El color ya está presente en el cuerpo
                }
            }
        }
        return false; // El color no está presente en el cuerpo
    }

    public void setSuTurno(boolean turno){
        this.SuTurno = turno;
    }

    public boolean isTurno(){
        return SuTurno;
    }
    public int CantCartasMano(){
        return mano.size();
    }
    public String getNombre(){
        return nombre;
    }
    public ArrayList<Carta> getMano(){
        return mano;
    }
    public ArrayList<ICarta> obtenerCartas(){
        ArrayList<ICarta> cartas = new ArrayList<>();
        for(Carta carta : mano){
            cartas.add(carta);
        }
        return cartas;
    }
    public ArrayList<ICarta> obtenerCuerpo(){
        ArrayList<ICarta> organos = new ArrayList<>();
        for(Organo organo : cuerpo){
            organos.add(organo);
        }
        return organos;
    }

    public boolean organosSanos(){
        for(Organo i : cuerpo){
            if(!i.estaSano()){
                return false;
            }
        }
        return true;
    }

    public void setMano(ArrayList<Carta> mano){
        this.mano = mano;
    }

    public void tomarCarta(Carta carta){
        mano.add(carta);
    }

    public void descartar(int carta){
        mano.remove(carta);
    }
}
