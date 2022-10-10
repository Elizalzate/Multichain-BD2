package UI;

import javax.swing.*;

public class VerObras {
    private JPanel frmVerObras;

    public void loadForm(){
        JFrame f = new JFrame("Home");
        f.setContentPane(new VerObras().frmVerObras);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }
}
