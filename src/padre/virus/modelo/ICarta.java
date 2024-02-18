package padre.virus.modelo;

public interface ICarta {


    String toString();

    Color getColor();

    boolean estaSano();
    boolean esInmune();
    Tipo getTipo();
}
