package padre.virus;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;
import padre.virus.modelo.IModelo;
import padre.virus.modelo.Modelo;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppServidor {

    public static void main(String[] args) {

            ArrayList<String> ips = Util.getIpDisponibles();
            String ip = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ips.toArray(),
                    null
            );
            String port = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    8888
            );
            IModelo modelo = (IModelo) Modelo.getInstancia();


            Servidor servidor = new Servidor(ip, Integer.parseInt(port));
            try {
                servidor.iniciar(modelo);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (RMIMVCException e) {
                e.printStackTrace();
            }


    }
}
