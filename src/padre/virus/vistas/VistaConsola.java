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
                    procesarEntrada(TextField.getText());
                    TextField.setText("");

                }
            }
        });

        frame.getRootPane().setDefaultButton(ENTER);
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
        printear("\n----------------------------------------------------------------------",Color.orange);
        printear("\ntus cartas: \n",Color.CYAN);
        int i = 1;
        for(String carta : cartas){
            printear(i + "\n - " + carta + "\n",Color.MAGENTA);
            i++;
        }
    }

    public void mostrarCuerpo(ArrayList<String> organos){
        printear("\n----------------------------------------------------------------------",Color.orange);
        printear("\nTu Cuerpo\n",Color.cyan);
        for(String organo : organos){
            printear("\n" + organo + "\n",Color.white);
        }
    }

    public void mostrarCuerpoRival(ArrayList<String> organos){
        printear("\n----------------------------------------------------------------------",Color.orange);
        printear("\nCuerpo del Rival\n",Color.cyan);
        int i = 1;
        for(String organo : organos){
            printear(i +"\n" + organo + "\n",Color.white);
            i++;
        }
    }

    @Override
    public void mostrarTurno(String jugadorActual) {
        printear("\nEs el turno del jugador: "+jugadorActual,Color.MAGENTA);
    }

    @Override
    public void mostrarJugadores(ArrayList<String> jugadores) {
        for(String jugador : jugadores){
            printear("\n1 - " + jugador + "\n",Color.ORANGE);
        }
    }

    @Override
    public void mostrarNuevoJugador(String jugador) {
        printear("\n\nEl jugador " + jugador + " se unio correctamente\n\n",Color.green);

    }

    @Override
    public void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas,ArrayList<String> organos) {
        printear("-----------------------------------\n",Color.MAGENTA);
        printear("|        partida iniciada         |\n",Color.MAGENTA);
        printear("-----------------------------------\n",Color.MAGENTA);
        mostrarTurno(jugadorActual);
        mostrarCuerpo(organos);
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
