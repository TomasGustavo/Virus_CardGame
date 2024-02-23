package padre.virus.modelo;

import padre.virus.vistas.ColorRGB;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class DialogMostrarReglas extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane text;
    private JPanel contenerdor;

    public DialogMostrarReglas() {
        setContentPane(contentPane);
        contentPane.setBackground(ColorRGB.DARK_TIEL);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        mostrarReglas();
    }

    private void mostrarReglas(){
        text.setForeground(ColorRGB.PINK);
        text.setOpaque(false);
        text.setEditable(false); // Deshabilitar la edición del JTextArea
        contenerdor.setBackground(ColorRGB.DARK_TIEL);

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        text.setText("\n\t\t REGLAS DE VIRUS!\n\nEste juego es un juego rápido donde \n" +
                "                 las partidas pueden llegar a durar unos 20 minutos y está \n" +
                "                destinado a jugar entre 2 y 6 jugadores.\n\ncontiene un mazo de 68 cartas divididas en los siguientes tipos:\n\n" +
                "                \n" +
                "                 - 21 cartas de órganos (5 corazones; 5 estómagos; 5 cerebros-, 5 huesos; 1 órgano comodín)\n\n" +
                "                 - 17 cartas de virus (4 rojos; 4 verdes; 4 azules; 4 amarillos; 1 comodín)\n\n" +
                "                 - 20 cartas de medicinas (4 rojos; 4 verdes; 4 azules; 4 amarillos; 4 comodín)\n\n" +
                "                 - 10 cartas de Tratamientos\n\nEl primer jugador que consiga 4 órganos totalmente sanos es el que gana.\n" +
                "                Se consideran órganos sanos los órganos que no tienen infección, que están vacunados o los\n" +
                "                que están inmunizados.\n\nPor turnos cada jugador podrá usar una de las 3 cartas que tenga en su mano." + "¿Que puedes  hacer con tu cartas?" +
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
                "Gana el primer jugador que tenga delante de el, en su cuerpo 4 órganos sanos.");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setAlignmentY(Component.CENTER_ALIGNMENT);
    }
    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
