package UI;

import javax.swing.*;

public class SubirObra {
    private JPanel frmSubirObra;

    public void loadForm(){
        JFrame f = new JFrame("Home");
        f.setContentPane(new SubirObra().frmSubirObra);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }
}
