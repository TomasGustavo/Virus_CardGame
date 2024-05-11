package padre.virus.modelo;

public interface ICarta {


    String toString();

    Color getColor();

    boolean accion(Organo organo);

    boolean estaSano();
    boolean esInmune();
    Tipo getTipo();
}
