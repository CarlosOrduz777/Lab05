package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class JewelQuestGUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem nuevo,abrir,salvar,salvarComo,salir;

    private JewelQuestGUI(){
        this.prepareElementos();
        this.prepareAcciones();
    }

    private void prepareElementos(){
        setTitle("JewelQuest");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth()/2,(int)d.getHeight()/2);
        setLocationRelativeTo(null);

        prepareElementosMenu();
    }
    private void prepareElementosMenu(){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Menu");
        menuBar.add(menu);
        nuevo = new JMenuItem("Nuevo");
        menu.add(nuevo);
        abrir = new JMenuItem("Abrir");
        menu.add(abrir);
        salvar = new JMenuItem("Salvar");
        menu.add(salvar);
        salvarComo = new JMenuItem("Salvar Como");
        menu.add(salvarComo);
        salir = new JMenuItem("Salir");
        menu.add(salir);
    }
    private void prepareAcciones(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                close();
            }
        });
        prepareMenuAcciones();
    }

    private void prepareMenuAcciones(){
        salir.addActionListener(this);
        abrir.addActionListener(this);
        salvar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == salir){
            close();
        }else if(e.getSource() == abrir){
            abrirAccion();
        }else if(e.getSource() == salvar){
            salvarAccion();
        }
    }
    private void abrirAccion(){
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(abrir);
        if(opcion == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad de abrir esta en construccion, por lo tanto el archivo: "+file.getName()+" No se puede abrir");

        }
    }
    private void salvarAccion(){
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showSaveDialog(null);
        if(opcion == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "La funcionalidad de salvar esta en construccion, por lo tanto el archivo: "+file.getName()+" No se puede abrir");

        }
    }

    private void close(){
        if (JOptionPane.showConfirmDialog(rootPane,"Desea terminar el programa?","Salir",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JewelQuestGUI gui = new JewelQuestGUI();
        gui.setVisible(true);
    }
    /*
    Cambios JewelQuestGUI
     */

}