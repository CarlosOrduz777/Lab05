package presentacion;

import org.w3c.dom.DOMImplementation;

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
    private JLabel fondo;
    private JPanel grillaBotones;
    private JButton jugar;
    private JButton scoreBoard;
    private JLabel titulo;
    private JPanel principal;

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
        prepareElementosPrincipal();
    }
    private void prepareElementosPrincipal(){
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon(getClass().getResource("fondo.jpg")));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fondo.setBounds(270,10,200,70);

        titulo = new JLabel("JEWEL  \n"+"QUEST");
        principal = new JPanel();
        principal.add(fondo);
        principal.add(titulo);
        add(principal);


        grillaBotones = new JPanel();
        Dimension dimension = new Dimension();
        dimension.setSize(40,30);
        grillaBotones.setSize(dimension);
        grillaBotones.setLayout(new GridLayout(2,1));
        grillaBotones.setLocation(50,50);
        jugar = new JButton("Play");
        Dimension d2 = new Dimension();
        d2.setSize((int)dimension.getWidth()/20,(int)dimension.getHeight()/20);
        jugar.setSize(d2);
        grillaBotones.add(jugar);
        scoreBoard = new JButton("Score Board");
        scoreBoard.setSize(d2);
        grillaBotones.add(scoreBoard);

        add(grillaBotones);




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