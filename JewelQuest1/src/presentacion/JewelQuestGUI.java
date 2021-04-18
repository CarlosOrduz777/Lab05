package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JewelQuestGUI extends JFrame{

    private JewelQuestGUI(){
        this.prepareElementos();
        this.prepareAcciones();
    }

    private void prepareElementos(){
        setTitle("JewelQuest");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) d.getWidth()/2,(int)d.getHeight()/2);
        setLocationRelativeTo(null);
    }

    private void prepareAcciones(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                close();
            }
        });
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