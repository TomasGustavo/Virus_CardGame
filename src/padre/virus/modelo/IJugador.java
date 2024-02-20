package padre.virus.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public interface IJugador{

    ArrayList<Organo> getCuerpo();
    boolean setCuerpo(Carta organo);
    void setSuTurno(boolean turno);
    boolean isTurno();
    String getNombre();
    int getID();
    ArrayList<Carta> getMano();
    ArrayList<ICarta> obtenerCartas();
    ArrayList<ICarta> obtenerCuerpo();
    boolean organosSanos();
    void setMano(ArrayList<Carta> mano);
    void tomarCarta(Carta carta);
    void descartar(int carta);

}
