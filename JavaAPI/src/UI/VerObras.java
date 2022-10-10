package UI;

import com.google.gson.internal.LinkedTreeMap;
import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;
import multichain.object.StreamKeyItem;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VerObras {
    private String nombreObra;
    private String autor;
    private JPanel frmVerObras;
    private JTextPane txtNombreObra;
    private JTextPane txtAutor;
    private JButton btnBuscar;
    private JTable resultados;

    public VerObras() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setProperties();
                if(! txtAutor.getText().equals("") && ! txtNombreObra.getText().equals("")){
                    verObra(autor, nombreObra);
                    clearTxt();
                }
                else if(! txtAutor.getText().equals("")){
                    verObrasPorAutor(autor);
                    clearTxt();
                }
                else if(! txtNombreObra.getText().equals("")){
                    verObraPorNombreObra(nombreObra);
                    clearTxt();
                }
            }
        });
    }

    private void setProperties(){
        this.nombreObra = txtNombreObra.getText().trim();
        this.autor = txtAutor.getText().trim();
    }

    private void clearTxt(){
        txtAutor.setText("");
        txtNombreObra.setText("");
    }

    public void loadForm(){
        JFrame f = new JFrame("Home");
        f.setContentPane(new VerObras().frmVerObras);
        f.pack();
        f.setLocation(400, 65);
        f.setVisible(true);
        f.setResizable(false);
    }

    public static List<List<String>> verObrasPorAutor (String nombreAutor){
        CommandManager commandManager = new CommandManager("localhost", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");

        List<List<String>> array = new ArrayList<>();
        try {

            List<StreamKeyItem> items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Obras");

            for(int i= 0; i<items.size(); i++){
                List<String> lista= new ArrayList<>();
                List<String> list = items.get(i).getKeys();
                if(list.contains(nombreAutor)){
                    LinkedTreeMap tree = (LinkedTreeMap) items.get(i).getData();
                    LinkedTreeMap tree2= (LinkedTreeMap) tree.get("json");
                    lista.add(nombreAutor);
                    lista.add((String)tree2.get("fecha_creacion"));
                    lista.add((String)tree2.get("obra"));
                    array.add(lista);
                }
            }

        } catch (MultichainException e) {
            e.printStackTrace();

        }
        System.out.println(array);
        return array;
    }

    public static List<List<String>> verObraPorNombreObra (String nombreObra){
        CommandManager commandManager = new CommandManager("localhost", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");

        List<List<String>> array = new ArrayList<>();
        try {

            List<StreamKeyItem> items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Obras");

            for(int i= 0; i<items.size(); i++){
                List<String> lista= new ArrayList<>();
                List<String> list = items.get(i).getKeys();
                if(list.contains(nombreObra)){
                    LinkedTreeMap tree = (LinkedTreeMap) items.get(i).getData();
                    LinkedTreeMap tree2= (LinkedTreeMap) tree.get("json");
                    lista.add(nombreObra);
                    lista.add((String)tree2.get("fecha_creacion"));
                    lista.add((String)tree2.get("obra"));
                    array.add(lista);
                }
            }

        } catch (MultichainException e) {
            e.printStackTrace();

        }
        System.out.println(array);
        return array;
    }

    public static List<List<String>> verObra (String nombreAutor, String nombreObra){
        CommandManager commandManager = new CommandManager("localhost", "9540", "multichainrpc", "BQD76dFwZzMFdD6AvgsBA327rHy7TE5B7JyYtX5L8bwo");

        List<List<String>> array = new ArrayList<>();
        try {

            List<StreamKeyItem> items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Obras");

            for(int i= 0; i<items.size(); i++){
                List<String> lista= new ArrayList<>();
                List<String> list = items.get(i).getKeys();
                if(list.contains(nombreObra) && list.contains(nombreAutor)){
                    LinkedTreeMap tree = (LinkedTreeMap) items.get(i).getData();
                    LinkedTreeMap tree2= (LinkedTreeMap) tree.get("json");
                    lista.add(nombreAutor);
                    lista.add(nombreObra);
                    lista.add((String)tree2.get("fecha_creacion"));
                    lista.add((String)tree2.get("obra"));
                    array.add(lista);
                }
            }

        } catch (MultichainException e) {
            e.printStackTrace();

        }
        System.out.println(array);
        return array;
    }
}
