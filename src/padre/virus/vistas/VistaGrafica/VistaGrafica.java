package padre.virus.vistas.VistaGrafica;

import padre.virus.gameController.Controlador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.IVista;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.System.exit;

public class VistaGrafica implements IVista {
    private Controlador controlador;
    private JPanel pnlPrincipal;
    private JLabel lblNotificaciones;
    private JSplitPane splPrincipal;
    private JPanel pnlMenu;
    private JPanel pnlCardNuevoJugador;
    private JPanel pnlContenedor;
    private JTextField txtNombreJugador;
    private JButton btnAceptar;
    private JLabel lblNombreJugador;
    private JPanel pnlCardMenuPrincipal;
    private JLabel lblTitulo;
    private JButton btnNuevaPartida;
    private JButton btnMostrarJugadores;
    private JButton btnReglas;
    private JButton btnSalir;
    private JPanel pnlMenuPrincipal;
    private JPanel pnlChat;
    private JScrollPane scpChat;
    private JTextField txtEscritura;
    private JLabel lblTituloChat;
    private JTextArea txtLog;
    private JTextPane txpChat;
    private JMenuBar mnbMenu;
    private JMenu mnPartida;
    private JMenu mnOpciones;
    private JMenu mnAcercade;
    private JMenuItem mniSalir;
    private JCheckBox cbxDesabilitarChat;
    private JMenuItem mniMostrarReglas;
    private JMenuItem mniComoJugar;

    private JFrame frame;

    public VistaGrafica(int x, int y) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Virus");
                frame.setContentPane(pnlPrincipal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(500, 500));
                frame.setPreferredSize(new Dimension(600, 600));
                frame.setLocation(x, y);
                frame.setVisible(true);

                cbxDesabilitarChat.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED){
                            pnlChat.setVisible(false);
                        } else{
                            pnlChat.setVisible(true);
                            splPrincipal.setDividerLocation(0.8);
                        }
                        pnlPrincipal.revalidate();
                        pnlPrincipal.repaint();
                    }
                });


                txtEscritura.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            controlador.actualizarChat(txtEscritura.getText(),txtNombreJugador.getText());
                            txtEscritura.setText("");
                        }
                    }
                });

                btnAceptar.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (!txtNombreJugador.getText().isEmpty()) {
                            controlador.AgregarJugador(txtNombreJugador.getText());
                            cambiarCardPanel(pnlCardMenuPrincipal);
                            notificarMensaje("El jugador "+txtNombreJugador.getText()+" se ha unido");
                        } else {
                            JOptionPane.showMessageDialog(pnlContenedor, "Por favor ingrese su nombre de usuario!",
                                    "ERROR!!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                btnMostrarJugadores.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controlador.listaJugadores();
                    }
                });

                btnSalir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exit(0);
                    }
                });

            }
        });
    }

    private void mostrarReglas() {

    }

    private void cambiarCardPanel(JPanel panelCard) {
        pnlMenu.removeAll();
        pnlMenu.add(panelCard);
        // Revalidar y repintar el panel
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;

    }

    @Override
    public void mostrar() {

    }

    @Override
    public void mostrarCartas(ArrayList<String> cartas) {

    }

    @Override
    public void mostrarCuerpo(ArrayList<String> organos) {

    }

    @Override
    public void mostrarCuerposEnLista(String jugador, ArrayList<String> jugadores) {

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
    public void mostarInicioPartido(String jugadorActual, ArrayList<String> cartas, ArrayList<String> organos) {

    }

    @Override
    public void mostrarNuevoJugador() {

    }

    @Override
    public void cartelInicioPartida() {

    }

    @Override
    public void mostrarTexto(String txt) {
        notificarMensaje(txt);
    }

    @Override
    public void terminarTurno() {

    }

    @Override
    public void HabilitarTurno() {

    }

    @Override
    public void partidaTerminada(String jugadorActual) {

    }

    @Override
    public void abandonoPartida(String nombre) {

    }


    @Override
    public void notificarMensaje(String texto) {
        lblNotificaciones.setText(texto);
    }

    // TODO cambiar lo que seria el log por un chat general para hablar

    public void mostrarChat(String texto, String jugador){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:MM: ");
        printear(LocalDateTime.now().format(formato), ColorRGB.CYAN);
        printear(jugador+": ",ColorRGB.YELLOW);
        printear(texto,ColorRGB.GREEN);
        printear("\n",Color.white);
    }

    public void printear(String texto, Color color) {
        StyledDocument doc = txpChat.getStyledDocument();
        Style style = txpChat.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        txpChat.setCaretPosition(txpChat.getDocument().getLength()); // Ajusta la posición del cursor al final del documento
    }


}
