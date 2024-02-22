package padre.virus;

import padre.virus.modelo.IJugador;
import padre.virus.modelo.Modelo;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class Save implements Serializable {

    private String nombreArchivo;
    private LocalDate fecha;
    private LocalTime hora;
    private Modelo partidaGuardada;
    private String host;

    public Save(String nombreArchivo,Modelo partida,String host){
        this.nombreArchivo = nombreArchivo;
        this.host = host;
        partidaGuardada = partida;
        fecha = LocalDate.now();
        hora = LocalTime.now().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
    }

    public Modelo getPartidaGuardada(){
        return partidaGuardada;
    }

    @Override
    public String toString() {
        return host+" - Nombre del Save: "+ nombreArchivo + " | Fecha: "+fecha+" - "+hora;
    }
}
