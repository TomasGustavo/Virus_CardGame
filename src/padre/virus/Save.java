package padre.virus;

import padre.virus.modelo.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Save implements Serializable {

    private String nombreArchivo;
    private LocalDate fecha;
    private LocalTime hora;
    private Modelo partidaGuardada;

    public Save(String nombreArchivo,Modelo partida){
        this.nombreArchivo = nombreArchivo;
        partidaGuardada = partida;
        fecha = LocalDate.now();
        hora = LocalTime.now();
    }

    public Modelo getPartidaGuardada(){
        return partidaGuardada;
    }

    @Override
    public String toString() {
        return "Nombre del Save: "+ nombreArchivo + " | Fecha: "+fecha+" - "+hora;
    }
}
