package padre.virus;


import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import padre.virus.gameController.Controlador;
import padre.virus.vistas.IVista;
import padre.virus.vistas.VistaConsola.VistaConsola;
import padre.virus.vistas.VistaGrafica.VistaGrafica;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppCliente {
    public static void main(String[] args) throws RemoteException, RMIMVCException {
        AppServidor server;
            ArrayList<String> ips = Util.getIpDisponibles();
            String ip = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ips.toArray(),
                    null
            );
            String port = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    9999
            );
            String ipServidor = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la IP en la corre el servidor", "IP del servidor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null
            );
            String portServidor = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    8888
            );
            ArrayList<String> vistasDisponibles = new ArrayList<>();
            vistasDisponibles.add("Consola");
            vistasDisponibles.add("Ventana Grafica");
            String visualizacion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la vista que quiere para ver el juego", "Visualizacion del juego",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    vistasDisponibles.toArray(),
                    null
            );
        //int x = Integer.parseInt(args[4]);
        //int y = Integer.parseInt(args[5]);
        IVista vista = null;
        Controlador controlador;
        if (visualizacion.equals("Ventana Grafica")) {
            vista = new VistaGrafica();
        } else {
            vista = new VistaConsola();
        }
        controlador = new Controlador(vista);
        vista.setControlador(controlador);
        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
        try {
            c.iniciar(controlador);
            vista.mostrarNuevoJugador();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RMIMVCException e) {
            e.printStackTrace();
        }

    };
}

