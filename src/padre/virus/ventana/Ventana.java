package padre.virus.ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import static java.lang.System.exit;


public class Ventana {

    JFrame frame;

    public Ventana(){
        iniciarVentana();
    }

    public void iniciarVentana(){
        // PARAMETROS DE LA VENTANA
        frame = new JFrame("Virus.exe");
        frame.setSize(1024,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);



        JPanel panelPrincipal = (JPanel) frame.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());


        JLabel etiqueta = new JLabel("VIRUS CARDGAME");
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        etiqueta.setPreferredSize(new Dimension(100,150));
        panelPrincipal.add(etiqueta,BorderLayout.NORTH);

        JLabel etiqueta2 = new JLabel("Desarrollado por TOMAS LOIACONO");
        etiqueta2.setHorizontalAlignment(JLabel.CENTER);
        etiqueta2.setPreferredSize(new Dimension(100,50));
        panelPrincipal.add(etiqueta2,BorderLayout.SOUTH);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(3,1,5,25));

        JButton boton = new JButton("Nueva Partida");
        JButton boton2 = new JButton("Opciones");
        JButton boton3 = new JButton("Salir");
        boton3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                exit(0);

            }
        });

        boton.setPreferredSize(new Dimension(150,50));
        menu.add(boton);
        menu.add(boton2);
        menu.add(boton3);


        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new FlowLayout());
        menuContainer.add(menu);



        JPanel menuCentrado = new JPanel(new BorderLayout());
        menuCentrado.add(menuContainer);

        JLabel etiqueta3 = new JLabel(" ");
        etiqueta3.setHorizontalAlignment(JLabel.CENTER);

        etiqueta3.setPreferredSize(new Dimension(100,100));
        menuCentrado.add(etiqueta3,BorderLayout.NORTH);

        panelPrincipal.add(menuCentrado, BorderLayout.CENTER);

        frame.setVisible(true);
    }



}
