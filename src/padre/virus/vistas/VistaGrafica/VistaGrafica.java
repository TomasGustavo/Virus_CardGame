package padre.virus.vistas.VistaGrafica;

import padre.virus.gameController.Controlador;
import padre.virus.modelo.ICarta;
import padre.virus.modelo.IJugador;
import padre.virus.vistas.ColorRGB;
import padre.virus.vistas.IVista;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
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
    private JButton btnOpcSalir;
    private JButton btnOpcReglas;
    private JLabel lblEngranaje;
    private JLabel lblBackGround;
    private JPanel pnlCardPartida;
    private JPanel pnlJugadorWest;
    private JPanel pnlJugadorNorth;
    private JPanel pnlJugadorEast;
    private JPanel pnlJugadorSouth;
    private JPanel pnlMesaDeJuego;
    private JList lstManoSur;
    private JLabel lblNombreSur;
    private JScrollPane scpManoSur;
    private JPanel pnlMesa;
    private JScrollPane scpOrganosSur;
    private JList lstOrganosSur;
    private JScrollPane scpOrgnaosNorte;
    private JList lstOrganosNorte;
    private JScrollPane scpOrganosWest;
    private JList lstOrganosWest;
    private JScrollPane scpOrganosEast;
    private JList lstOrganosEast;
    private JLabel lblNombreWest;
    private JScrollPane scpManoWest;
    private JList lstManoWest;
    private JLabel lblNombreNorte;
    private JScrollPane scpManoNorte;
    private JList lstManoNorte;
    private JLabel lblNombreEast;
    private JScrollPane scpManoEast;
    private JList lstManoEast;
    private JPanel pnlCentroMesa;
    private JLabel lblMazo;
    private JLabel lblMazoDescarte;
    private JButton btnTerminarTurno;
    private JButton btnTirarVirus;
    private JButton btnCurar;
    private JButton btnDescartar;

    private DefaultListModel<ImageIcon> listaModeloSur;
    private DefaultListModel<ImageIcon> listaModeloSurOrganos;

    private DefaultListModel<ImageIcon> listaModeloNorte;
    private DefaultListModel<ImageIcon> listaModeloNorteOrganos;
    private DefaultListModel<ImageIcon> listaModeloEste;
    private DefaultListModel<ImageIcon> listaModeloEsteOrganos;
    private DefaultListModel<ImageIcon> listaModeloOeste;
    private DefaultListModel<ImageIcon> listaModeloOesteOrganos;

    private Image backgroundPartida = new ImageIcon("src/padre/virus/resources/imagenes/backGruond.jpg").getImage();

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

                // Crea el listModel necesario para mostrar las cartas de la mano
                listaModeloSur = new DefaultListModel<>();
                lstManoSur.setModel(listaModeloSur);
                lstManoSur.setBackground(ColorRGB.TIEL);
                lstManoSur.setVisibleRowCount(1);

                listaModeloSurOrganos = new DefaultListModel<>();
                lstOrganosSur.setModel(listaModeloSurOrganos);
                lstOrganosSur.setBackground(new Color(0, 0, 255));
                lstOrganosSur.setVisibleRowCount(1);

                listaModeloNorte = new DefaultListModel<>();
                lstManoNorte.setModel(listaModeloNorte);
                lstManoNorte.setBackground(ColorRGB.TIEL);
                lstManoNorte.setVisibleRowCount(1);

                listaModeloNorteOrganos = new DefaultListModel<>();
                lstOrganosNorte.setModel(listaModeloNorteOrganos);
                lstOrganosNorte.setBackground(new Color(0, 0, 255));
                lstOrganosNorte.setVisibleRowCount(1);

                splPrincipal.remove(pnlMenu);
                txtEscritura.setEnabled(false);

                JPanel pnlSuperpuesto = new JPanel();
                OverlayLayout overlayLayout = new OverlayLayout(pnlSuperpuesto);
                pnlSuperpuesto.setLayout(overlayLayout);
                pnlSuperpuesto.setPreferredSize(new Dimension(600,600));
                splPrincipal.add(pnlSuperpuesto);

                ImageIcon imgiEngranaje = new ImageIcon("src/padre/virus/resources/imagenes/Engranaje.png");
                JLabel lblEngranaje = new JLabel(imgiEngranaje);

                ImageIcon imgiMazo = new ImageIcon("src/padre/virus/resources/imagenes/Cartas/dorsomazo.png");
                lblMazo.setIcon(imgiMazo);
                lblMazoDescarte.setIcon(imgiMazo);

                lblEngranaje.setHorizontalAlignment(SwingConstants.LEFT);
                lblEngranaje.setVerticalAlignment(SwingConstants.TOP);


                JPanel pnlContenedorEngranaje = new JPanel();
                pnlContenedorEngranaje.setLayout(new BorderLayout());
                pnlContenedorEngranaje.add(lblEngranaje,BorderLayout.WEST);
                pnlContenedorEngranaje.setOpaque(false);

                pnlSuperpuesto.add(pnlContenedorEngranaje);
                pnlSuperpuesto.add(pnlMenu);

                splPrincipal.revalidate();
                splPrincipal.repaint();



                lblEngranaje.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        mostrarConfiguracion(lblEngranaje);
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
                            txtEscritura.setEnabled(true);
                            controlador.AgregarJugador(txtNombreJugador.getText());
                            agregarTitulo();
                            cambiarCardPanel(pnlCardMenuPrincipal);
                            notificarMensaje("El jugador "+txtNombreJugador.getText()+" se ha unido");
                        } else {
                            JOptionPane.showMessageDialog(pnlContenedor, "Por favor ingrese su nombre de usuario!",
                                    "ERROR!!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

                btnNuevaPartida.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        controlador.Jugar();
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

    private void mostrarConfiguracion(Component componente) {
        JWindow popUp = new JWindow();
        Box verticalBox = Box.createVerticalBox();

        JLabel lblFlechaAtras = new JLabel(new ImageIcon("src/padre/virus/resources/imagenes/FlechaAtras.png"));
        lblFlechaAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                popUp.setVisible(false);
            }
        });

        frame.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentMoved(ComponentEvent e) {

                popUp.setLocation(frame.getLocationOnScreen().x+8,frame.getLocationOnScreen().y+32); // mueve el popUp conforme se va moviendo el frame
            }
        });


        JPanel panelIntermedio = new JPanel(new BorderLayout());
        panelIntermedio.setBackground(new Color(169, 177, 176, 250));

        panelIntermedio.add(lblFlechaAtras,BorderLayout.WEST);
        lblFlechaAtras.setHorizontalAlignment(SwingConstants.LEFT);
        lblFlechaAtras.setVerticalAlignment(SwingConstants.TOP);

        verticalBox.setBorder(BorderFactory.createEmptyBorder(50,0,10,10));

        panelIntermedio.add(verticalBox,BorderLayout.CENTER);

        popUp.setSize(200, splPrincipal.getHeight()); // setea el tamanio del popUp en base a la altura de la pantalla
        popUp.setLocation(frame.getLocationOnScreen().x+8,frame.getLocationOnScreen().y+32); // pone el popUp en la esquina superiror izquierda
        popUp.setBackground(new Color(255, 255, 255, 250)); // alfa aplica transparencia

        JCheckBox deshabilitarChat = getjCheckBox();
        btnOpcSalir = getbtnOpcSalir();
        btnOpcReglas = getbtnOpcReglas();

        verticalBox.add(deshabilitarChat);
        verticalBox.add(Box.createRigidArea(new Dimension(0,30)));

        verticalBox.add(btnOpcReglas);
        verticalBox.add(Box.createRigidArea(new Dimension(0,30)));

        verticalBox.add(btnOpcSalir);

        popUp.getContentPane().setLayout(new BoxLayout(popUp.getContentPane(),BoxLayout.Y_AXIS));
        popUp.getContentPane().add(panelIntermedio);

        popUp.setVisible(true);


    }


    private JButton getbtnOpcReglas() {
        JButton reglas = new JButton();
        reglas.setText("Mostrar Reglas");
        reglas.setBackground(ColorRGB.PINK);
        reglas.setForeground(Color.black);
        reglas.setMaximumSize(new java.awt.Dimension(150, 30));
        reglas.setAlignmentX(Component.CENTER_ALIGNMENT);
        reglas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarReglas();
            }
        });
        return reglas;
    }

    private JButton getbtnOpcSalir() {
        JButton salir = new JButton();
        salir.setText("Abandonar Partida");
        salir.setBackground(ColorRGB.PINK);
        salir.setForeground(Color.black);
        salir.setMaximumSize(new java.awt.Dimension(150, 30));
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });
        return salir;
    }

    /**
     * @return deshabilitarChat ya seteado con todos los parametros
     */
    private JCheckBox getjCheckBox() {
        JCheckBox deshabilitarChat = new JCheckBox();
        deshabilitarChat.setText("Deshabilitar Chat");
        deshabilitarChat.setBackground(ColorRGB.PINK);
        deshabilitarChat.setOpaque(true);
        deshabilitarChat.setForeground(Color.black);
        deshabilitarChat.setMaximumSize(new java.awt.Dimension(150, 30));
        deshabilitarChat.setAlignmentX(Component.CENTER_ALIGNMENT);
        deshabilitarChat.addItemListener(new ItemListener() {
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
        return deshabilitarChat;
    }

    private void agregarTitulo() {

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
    public void mostrarCartas(ArrayList<ICarta> cartas) throws RemoteException {
        listaModeloSur.removeAllElements();
        //System.out.println(cartas.size());
        for (ICarta carta : cartas) {
            String tempTipo = String.valueOf(carta.getTipo());
            tempTipo.toLowerCase();

            String tempColor= String.valueOf(carta.getColor());
            tempColor.toLowerCase();

            String imagenActual = "src/padre/virus/resources/imagenes/Cartas/" +tempTipo + tempColor + ".png";
            //String imagenActual = "src/padre/virus/resources/imagenes/Cartas/curarojo.png";
            ImageIcon cartaActual = new ImageIcon(imagenActual);

            listaModeloSur.addElement(cartaActual);
        }
        //System.out.println(listaModeloSur.size());
        mostrarManoJugadores();
        scpManoSur.setPreferredSize(new Dimension(lstManoSur.getPreferredSize().width, lstManoSur.getPreferredSize().height));
        // Revalidar y repintar el panel
        scpManoSur.revalidate();
        scpManoSur.repaint();
        frame.revalidate();
        frame.repaint();
    }

    private void mostrarManoJugadores() throws RemoteException {
        int jugadorID = (controlador.getIdJA()+1)%controlador.listaJugadores().size();
        String jugadorNombre = controlador.listaJugadores().get(jugadorID);
        int cantidadJugadores = controlador.listaJugadores().size()-1;
        switch (cantidadJugadores){
            case 1 -> actualizarManoNorte(controlador.getManoContrincante(jugadorNombre));
            //case 2 -> actualizarManoOeste(controlador.getManoContrincante(jugadorNombre));
            //case 3 -> actualizarManoEste(controlador.getManoContrincante(jugadorNombre));
        }

    }

    private void actualizarManoNorte(ArrayList<ICarta> cartas){
        listaModeloNorte.removeAllElements();
        for(int i = 0 ; i<cartas.size();i++){
            ImageIcon cartaActual = new ImageIcon("src/padre/virus/resources/imagenes/Cartas/dorsomazo.png");
            listaModeloNorte.addElement(cartaActual);
        }
        scpManoNorte.setPreferredSize(new Dimension(lstManoNorte.getPreferredSize().width, lstManoNorte.getPreferredSize().height));
        // Revalidar y repintar el panel
        scpManoNorte.revalidate();
        scpManoNorte.repaint();
        frame.revalidate();
        frame.repaint();

    }

    @Override
    public void mostrarCuerpo(ArrayList<ICarta> organos) { // TODO hacer el mostrar organos de los jugadores

        lstOrganosSur.setAlignmentX(Component.CENTER_ALIGNMENT);
        listaModeloSurOrganos.removeAllElements();
        for (ICarta organo : organos) {
            System.out.println(organo.toString());
            String tempTipo = String.valueOf(organo.getTipo());
            tempTipo.toLowerCase();

            String tempColor= String.valueOf(organo.getColor());
            tempColor.toLowerCase();

            String imagenActual = "src/padre/virus/resources/imagenes/Cartas/" +tempTipo + tempColor + ".png";
            ImageIcon cartaActual = new ImageIcon(imagenActual);

            listaModeloSurOrganos.addElement(cartaActual);
        }
        scpOrganosSur.setPreferredSize(new Dimension(lstOrganosSur.getPreferredSize().width, lstOrganosSur.getPreferredSize().height));
        // Revalidar y repintar el panel
        scpOrganosSur.revalidate();
        scpOrganosSur.repaint();
        frame.revalidate();
        frame.repaint();

    }

    @Override
    public void mostrarCuerposEnLista(String jugador, ArrayList<String> jugadores) {

    }

    @Override
    public void mostrarTurno(IJugador jugadorActual) {
        lblNotificaciones.setText("Es el turno del Jugador "+jugadorActual.getNombre());

    }

    @Override
    public void mostrarJugadores(ArrayList<String> jugadores) {

    }

    @Override
    public void mostrarNuevoJugador(String jugador) {

    }

    @Override
    public void mostarInicioPartido(IJugador jugadorActual, ArrayList<ICarta> cartas, ArrayList<ICarta> organos) throws RemoteException {
        mostrarTurno(jugadorActual);
        pnlJugadorEast.setVisible(false);
        pnlJugadorWest.setVisible(false);
        scpOrganosEast.setVisible(false);
        scpOrganosWest.setVisible(false);
        pnlJugadorNorth.setVisible(true);
        scpManoSur.getViewport().setOpaque(false);

        cambiarCardPanel(pnlCardPartida);

        lblNombreSur.setForeground(ColorRGB.CYAN);
        lblNombreSur.setText(" " + controlador.getNombre().toUpperCase() + " ");
        lblNombreNorte.setText(controlador.getOponente().toUpperCase());
        mostrarCartas(cartas);
        mostrarCuerpo(organos);

    }

    @Override
    public void mostrarNuevoJugador() {
        notificarMensaje("El jugador "+controlador.getNombre()+" se ha unido a la partida");
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
        lblNotificaciones.setText(texto.toUpperCase());
        lblNotificaciones.setBackground(Color.black);
        lblNotificaciones.setForeground(ColorRGB.TIEL);
    }



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
