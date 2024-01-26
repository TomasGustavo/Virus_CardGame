package padre.virus.vistas;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.Flujos.*;

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
    /*public void printearNotificacion(String texto,Color color){
        StyledDocument doc = textPane2.getStyledDocument();
        Style style = textPane2.addStyle("Style",null);
        StyleConstants.setForeground(style,color);
        textPane2.setCaretPosition(textPane2.getDocument().getLength());
        try{
            doc.insertString(doc.getLength(),texto,style);
        } catch (BadLocationException e){
            e.printStackTrace();
        }
    }*/

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
        printear("\n----------------------------------------------------------------------",ColorRGB.ORANGE);
        printear("\ntus cartas: \n",ColorRGB.CYAN);
        int i = 1;
        for(String carta : cartas){
            printear(i + " - " + carta + "\n",ColorRGB.PINK);
            i++;
        }
        printear("\nCartas restantes en el  mazo: " + controlador.obtenerMazo(),ColorRGB.BLUE);
    }

    public void mostrarCuerpo(ArrayList<String> organos){

        printear("\n----------------------------------------------------------------------",ColorRGB.ORANGE);
        printear("\nTu Cuerpo\n",ColorRGB.CYAN);
        int i = 1;
        for(String organo : organos){
            printear(i+" - " + organo + "\n",ColorRGB.TIEL);
            i++;
        }
    }

    public void mostrarCuerposEnLista(String jugador,ArrayList<String> jugadores){
        for(String i : jugadores){
            if(!i.equals(jugador)){
                //printear("\n----------------------------------------------------------------------",Color.orange);
                printear("\nCuerpo de "+i+": ",ColorRGB.CYAN);
                int j = 1;
                for(String organo : controlador.obtenerOrganos(i)){
                    printear(j +" - " + organo + "  |  ",Color.white);
                    j++;
                }
            }
        }
    }

    public void mostrarCuerpoRival(ArrayList<String> organos){
        printear("\n----------------------------------------------------------------------",ColorRGB.ORANGE);
        printear("\nCuerpo del Rival\n",ColorRGB.CYAN);
        int i = 1;
        for(String organo : organos){
            printear(i +" - " + organo + "\n ",Color.white);
            i++;
        }
        printear("\n",Color.white);
    }

    @Override
    public void mostrarTurno(String jugadorActual) {
        printear("\nEs el turno del jugador: "+jugadorActual,ColorRGB.ORANGE);
    }

    @Override
    public void mostrarJugadores(ArrayList<String> jugadores) {
        for(String jugador : jugadores){
            printear("\n1 - " + jugador + "\n",ColorRGB.ORANGE);
        }
    }

    @Override
    public void mostrarNuevoJugador(String jugador) {
        printear("\n\nEl jugador " + jugador + " se unio correctamente\n\n",ColorRGB.GREEN);

    }
    public void cartelInicioPartida(){
        printear("-----------------------------------\n",ColorRGB.MAGENTA);
        printear("|        partida iniciada         |\n",ColorRGB.MAGENTA);
        printear("-----------------------------------\n",ColorRGB.MAGENTA);
    }

    @Override
    public void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas,ArrayList<String> organos) {
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
    public void partidaTerminada(String jugadorActual) {
        flujoActual = new FlujoPartidaTerminada(this,controlador,jugadorActual,true);
        flujoActual.mostrarSiguienteTexto();
    }


    public void appendColorPosicion(String texto, Color color, int numeroLinea) {
        StyledDocument doc = textPane1.getStyledDocument();
        Style style = textPane1.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        // Obtener la posición de inicio y fin de la línea especificada
        Element root = doc.getDefaultRootElement();
        int startOffset = root.getElement(numeroLinea).getStartOffset();
        int endOffset = root.getElement(numeroLinea).getEndOffset();
        try {
            // Insertar el texto en la línea especificada
            doc.insertString(endOffset, texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        textPane1.setCaretPosition(endOffset); // Ajusta la posición del cursor al final de la línea
    }

    public void mostrarTexto(String txt) {
        appendColorPosicion(txt, ColorRGB.GREEN, 0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
