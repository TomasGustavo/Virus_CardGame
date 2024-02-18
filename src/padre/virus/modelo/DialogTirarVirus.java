package padre.virus.modelo;

import padre.virus.gameController.Controlador;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DialogTirarVirus extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private ArrayList<IJugador> jugadores;
    private IJugador jugadorActual;
    private Controlador controlador;
    private Integer IDCartaSeleccionada;
    private JComboBox cbxSeleccionarOponente;
    private JComboBox cbxSeleccionarOrgano;

    public DialogTirarVirus(ArrayList<IJugador> jugadores, IJugador jugadorActual, Controlador controlador,Integer IDCartaSeleccionada) {
        this.jugadores= jugadores;
        this.jugadorActual= jugadorActual;
        this.controlador = controlador;
        this.IDCartaSeleccionada= IDCartaSeleccionada;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setOponentes();

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

        cbxSeleccionarOponente.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String jugadorSeleccionado = (String) cbxSeleccionarOponente.getSelectedItem();
                setOrganos(jugadorSeleccionado);
            }
        });
    }

    private void onOK() {
        // add your code here
        String jugadorDestino = (String) cbxSeleccionarOponente.getSelectedItem();
        int organoDestino = cbxSeleccionarOrgano.getSelectedIndex();
        controlador.tirarCarta(jugadorDestino,(IDCartaSeleccionada+1),organoDestino+1);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void setOponentes(){
        boolean bandera = false;
        for(IJugador jugador : jugadores){
            if(!jugador.getNombre().equals(jugadorActual.getNombre())){
                cbxSeleccionarOponente.addItem(jugador.getNombre());
                if(!bandera){
                    setOrganos(jugador.getNombre());
                    bandera=true;
                }
            }
        }
    }
    private void setOrganos(String jugadorSeleccionado){
        if(jugadorSeleccionado != null){
            ArrayList<ICarta> organos = controlador.obtenerOrganos(jugadorSeleccionado);
            cbxSeleccionarOrgano.removeAllItems();
            for(ICarta organo : organos){
                cbxSeleccionarOrgano.addItem(organo.toString());
            }
        }
    }


}
