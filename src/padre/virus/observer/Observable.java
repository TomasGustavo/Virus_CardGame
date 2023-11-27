package padre.virus.observer;

public interface Observable {
    public void notificar(Object evento);

    public void agregarObservador(Observador observador);

    public void eliminarObservador(Observador observador);

}
