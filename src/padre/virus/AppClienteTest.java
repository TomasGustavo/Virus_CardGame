package padre.virus;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import padre.virus.gameController.Controlador;
import padre.virus.vistas.IVista;
import padre.virus.vistas.VistaGrafica.VistaGrafica;

import java.rmi.RemoteException;

public class AppClienteTest {
    public static void main(String[] strings) {
        String ip = strings[0];
        String port = strings[1];
        String ipServidor = strings[2];
        String portServidor = strings[3];





        /*
        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                ips.toArray(),
                null
        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                9999
        );
        String ipServidor = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la corre el servidor", "IP del servidor",
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null
        );
        String portServidor = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                8888
        );*/

        //int x = Integer.parseInt(args[4]);
        //int y = Integer.parseInt(args[5]);
        //IVista vista = new VistaConsola(x, y);
        IVista vista = new VistaGrafica();
        Controlador controlador = new Controlador(vista);

        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));

        try {
            c.iniciar(controlador);
            vista.mostrarNuevoJugador();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RMIMVCException e) {
            e.printStackTrace();
        }

    }
}