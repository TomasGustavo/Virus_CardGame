package padre.virus.vistas.Flujos;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.VistaConsola;

import java.awt.*;

public class FlujoMostrarReglas extends Flujo{

    public FlujoMostrarReglas(VistaConsola vista, Controlador controlador){
        super(vista,controlador);
    }
    @Override
    public Flujo procesarEntrada(String string) {
        return new FlujoMenuPrincipal(vista,controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.printear("\n\t\t Reglas del Virus!\n\n", Color.MAGENTA);
        vista.printear("Este juego es un juego rápido donde" +
                " las partidas pueden llegar a durar unos 20 minutos y está " +
                "destinado a jugar entre 2 y 6 jugadores.\n\n",Color.magenta);
        vista.printear("contiene un mazo de 68 cartas divididas en los siguientes tipos:\n" +
                "\n" +
                " - 21 cartas de órganos (5 corazones; 5 estómagos; 5 cerebros-, 5 huesos; 1 órgano comodín)\n" +
                " - 17 cartas de virus (4 rojos; 4 verdes; 4 azules; 4 amarillos; 1 comodín)\n" +
                " - 20 cartas de medicinas (4 rojos; 4 verdes; 4 azules; 4 amarillos; 4 comodín)\n" +
                " - 10 cartas de Tratamientos\n\n",Color.MAGENTA);
        vista.printear("El primer jugador que consiga 4 órganos totalmente sanos es el que gana. " +
                "Se consideran órganos sanos los órganos que no tienen infección, que están vacunados o los " +
                "que están inmunizados.\n\n",Color.MAGENTA);
        vista.printear("Por turnos cada jugador podrá usar una de las 3 cartas que tenga en su mano." + "¿Que puedes  hacer con tu cartas?" +
                " -  Poner un órgano delante de el en su parte de la mesa (cuerpo)\n" +
                " -  Podrás usar una medicina para curar un órgano tuyo infectado o vacunarlo para que no te lo infecten.\n" +
                " -  Infectar con un virus el órgano de un rival.\n" +
                " -  Usar un tratamiento.\n" +
                " -  Podrás descartarte de tantas cartas como quieras.\n" +
                "\n" +
                "En cada turno SÓLO podrás usar una carta para jugarla en la mesa o hasta las 3 para descartarlas y robar otras.\n" +
                "\n" +
                "Los jugadores SIEMPRE tendrán que tener 3 cartas en la mano. Si juegan una, automáticamente roban otra; si se descartan de 2 o 3, robarán 2 o 3 nuevas.\n" +
                "\n" +
                "Entonces ya se pasa el turno al siguiente jugador.\n" +
                "\n" +
                "Gana el primer jugador que tenga delante de el, en su cuerpo 4 órganos sanos.",Color.MAGENTA);
        vista.printear("Presione cualquier tecla para volver ....",Color.ORANGE);
    }
}
