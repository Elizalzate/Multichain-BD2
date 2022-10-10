package UI;

import javax.swing.*;

public class RegistrarAutor {
    private JPanel frmRegistrarAutor;

    public void loadForm(){
        JFrame f = new JFrame("Home");
        f.setContentPane(new RegistrarAutor().frmRegistrarAutor);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }
}
