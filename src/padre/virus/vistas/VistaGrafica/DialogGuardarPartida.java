package padre.virus.vistas.VistaGrafica;

import padre.virus.Save;
import padre.virus.gameController.Controlador;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DialogGuardarPartida extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblNombrePartida;
    private JTextField txtNombrePartida;
    private JComboBox cbxListaPartidas;
    private Controlador controlador;
    boolean esGuardar;

    public DialogGuardarPartida(Controlador controlador, boolean esGuardar) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.controlador = controlador;
        this.esGuardar = esGuardar;


        buttonOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onOK(esGuardar);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

        setSaves();
        if(getOpcion().equals(" <SLOT LIBRE> ")){
            deshabilitarEntradaTexto();
        }
    }

    private void onOK(boolean esGuardar) {
        if(esGuardar){
            controlador.guardarPartida(getNombre());
        }else{
            controlador.cargarPartida(getIndexOpcion());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public String getNombre(){
        return txtNombrePartida.getText();
    }

    public String getOpcion(){
        return cbxListaPartidas.getSelectedItem().toString();
    }
    public int getIndexOpcion(){
        return cbxListaPartidas.getSelectedIndex();
    }

    public void deshabilitarEntradaTexto(){
        lblNombrePartida.setVisible(false);
        txtNombrePartida.setVisible(false);
    }

    private void setSaves() {
        ArrayList<String> saves = controlador.getPartidasGuardadas();
        String j = " <SLOT LIBRE> ";
        cbxListaPartidas.removeAllItems();
        if (!saves.isEmpty()) {
            for (String i : saves) {
                cbxListaPartidas.addItem(i);
            }
        }
        for (int i = saves.size(); i < 4; i++) {
            cbxListaPartidas.addItem(i+"- "+j);
        }

    }
}
