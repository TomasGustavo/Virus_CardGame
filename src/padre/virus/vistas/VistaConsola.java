package padre.virus.vistas;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.Flujos.Flujo;
import padre.virus.vistas.Flujos.FlujoEsperandoTurno;
import padre.virus.vistas.Flujos.FlujoJugar;
import padre.virus.vistas.Flujos.FlujoNuevoUsuario;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class VistaConsola implements IVista{

    private Controlador controlador;
    private JButton ENTER;
    private JPanel panel1;
    private JTextField TextField;
    private JFrame frame;
    private JTextPane textPane1;
    private Flujo flujoActual;

    public VistaConsola(){
        frame  = new JFrame("Virus!");
        frame.setContentPane(panel1);
        frame.setSize(750,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ENTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printear(TextField.getText() + "\n", Color.WHITE);
                procesarEntrada(TextField.getText());
                TextField.setText("");
            }
        });

        TextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    printear(TextField.getText() + "\n",Color.WHITE);
                    TextField.setText("");
                }
            }
        });
    }

    public void printear(String texto, Color color){
        StyledDocument doc = textPane1.getStyledDocument();
        Style style = textPane1.addStyle("Style",null);
        StyleConstants.setForeground(style,color);
        textPane1.setCaretPosition(textPane1.getDocument().getLength());
        try{
            doc.insertString(doc.getLength(),texto,style);
        } catch (BadLocationException e){
            e.printStackTrace();
        }

    }

    public void desEntradas(){
        TextField.setEnabled(false);
        ENTER.setEnabled(false);
    }
    public void habEntradas(){
        TextField.setEnabled(true);
        ENTER.setEnabled(true);
    }

    public void procesarEntrada(String input){
        input = input.trim();
        if(input.isEmpty()){
            return;
        }
        flujoActual = flujoActual.procesarEntrada(input);
        flujoActual.mostrarSiguienteTexto();
    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    @Override
    public void mostrarCartas(ArrayList<String> cartas) {

    }

    @Override
    public void mostrarTurno(String jugadorActual) {

    }

    @Override
    public void mostrarJugadores(ArrayList<String> jugadores) {

    }

    @Override
    public void mostrarNuevoJugador(String jugador) {

    }

    @Override
    public void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas) {
        printear("-----------------------------------\n",Color.MAGENTA);
        printear("|                                  |\n",Color.MAGENTA);
        printear("-----------------------------------\n",Color.MAGENTA);
        mostrarTurno(jugadorActual);
        mostrarCartas(cartas);
        if(!controlador.isTurno()){
            flujoActual = new FlujoEsperandoTurno(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        } else if (controlador.isTurno()){
            habEntradas();
            flujoActual = new FlujoJugar(this,controlador);
            flujoActual.mostrarSiguienteTexto();
        }
    }

    public void mostarInicioPartido() {
        mostrar();
        flujoActual = new FlujoNuevoUsuario(this,controlador);
        flujoActual.mostrarSiguienteTexto();
    }

    @Override
    public void terminarTurno() {
        desEntradas();
    }

    @Override
    public void HabilitarTurno() {
        habEntradas();
        flujoActual = new FlujoJugar(this,controlador);
        flujoActual.mostrarSiguienteTexto();
    }

    @Override
    public void partidaTerminada(String jugadorActual, boolean hayGanador) {

    }
}
