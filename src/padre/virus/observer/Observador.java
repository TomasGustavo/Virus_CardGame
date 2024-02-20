package padre.virus.observer;

import java.io.Serializable;

public interface Observador{
    public void actualizar(Object evento, Observable observado);
}
