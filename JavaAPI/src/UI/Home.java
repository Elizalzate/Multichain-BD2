package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class Home {
    private JPanel frmHome;
    private JButton btnRegistrarAutor;
    private JButton btnSubirObra;
    private JButton btnVerObras;


    public Home() {
        btnRegistrarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarAutor registro = new RegistrarAutor();
                registro.loadForm();
            }
        });
        btnSubirObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubirObra subida = new SubirObra();
                subida.loadForm();
            }
        });
        btnVerObras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerObras verObras = new VerObras();
                verObras.loadForm();
            }
        });
    }

    public void loadForm(){
        JFrame f = new JFrame("Home");
        f.setContentPane(new Home().frmHome);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocation(380, 60);
        f.setVisible(true);
        f.setResizable(false);
    }

}
