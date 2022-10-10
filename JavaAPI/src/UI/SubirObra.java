package UI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubirObra {
    private String nombreObra;
    private String autor;
    private String agnoPublicacion;
    private String imagen;
    private JPanel frmSubirObra;
    private JTextPane txtAutor, txtImagen, txtNombreObra, txtAgnoPublicacion;
    private JButton btnSubirObra;

    public SubirObra() {
        btnSubirObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProperties();
                subirObra(nombreObra, autor, agnoPublicacion, imagen);
                clearTxt();
            }
        });
    }

    private void setProperties(){
        this.nombreObra = txtNombreObra.getText().trim();
        this.autor = txtAutor.getText().trim();
        this.agnoPublicacion = txtAgnoPublicacion.getText().trim();
        this.imagen = txtImagen.getText().trim();
    }

    private void clearTxt(){
        txtAutor.setText("");
        txtImagen.setText("");
        txtNombreObra.setText("");
        txtAgnoPublicacion.setText("");
    }

    public void loadForm(){
        JFrame f = new JFrame("Subir obras");
        f.setContentPane(new SubirObra().frmSubirObra);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }

    public void subirObra(String nombre, String autor, String agnoPublicacion, String imagen){

        CommandManager commandManager = new CommandManager("localhost", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");
        try {
            LinkedTreeMap arbolInicial = new LinkedTreeMap();
            arbolInicial.put("fecha_creacion", agnoPublicacion);
            arbolInicial.put("obra", imagen);
            LinkedTreeMap arbolElementos= new LinkedTreeMap();
            arbolElementos.put("json", arbolInicial);
            String[] key = {autor, nombre};
            Gson gson = new Gson();
            JsonObject arbolJSON = gson.toJsonTree(arbolElementos).getAsJsonObject();
            commandManager.invoke(CommandElt.PUBLISH, "Obras", key, arbolJSON);
            JOptionPane.showMessageDialog(frmSubirObra, "Registrado correctamente");

        } catch (MultichainException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
