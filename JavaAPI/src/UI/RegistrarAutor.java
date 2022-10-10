package UI;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import multichain.command.*;
import multichain.object.*;

public class RegistrarAutor {

    private String nombre;
    private String correo;
    private String agnoNacimiento;
    private JPanel frmRegistrarAutor;
    private JButton btnRegistrar;
    private JTextPane txtNombreAutor, txtCorreoAutor, txtAgnoNacimiento;
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager("192.168.1.3", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");

    }

    public RegistrarAutor() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProperties();
                registrarAutor(nombre, correo, agnoNacimiento);
                clearTxt();
            }
        });
    }

    public void loadForm(){
        JFrame f = new JFrame("Registrar autor");
        f.setContentPane(new RegistrarAutor().frmRegistrarAutor);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }

    private void setProperties(){
        this.nombre = txtNombreAutor.getText().trim();
        this.correo = txtCorreoAutor.getText().trim();
        this.agnoNacimiento = txtAgnoNacimiento.getText().trim();
    }
    private void clearTxt(){
        txtNombreAutor.setText("");
        txtCorreoAutor.setText("");
        txtAgnoNacimiento.setText("");
    }

    public void registrarAutor(String nombre, String correo, String agnoNacimiento){

        CommandManager commandManager = new CommandManager("localhost", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");
        try {
            LinkedTreeMap arbolInicial = new LinkedTreeMap();
            arbolInicial.put("nombre", nombre);
            arbolInicial.put("agno_nacimiento", agnoNacimiento);
            arbolInicial.put("correo", correo);
            LinkedTreeMap arbolElementos= new LinkedTreeMap();
            arbolElementos.put("json", arbolInicial);

            Gson gson = new Gson();
            JsonObject arbolJSON = gson.toJsonTree(arbolElementos).getAsJsonObject();

            commandManager.invoke(CommandElt.PUBLISH, "Autores", nombre, arbolJSON);
            JOptionPane.showMessageDialog(frmRegistrarAutor, "Registrado correctamente");

        } catch (MultichainException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
