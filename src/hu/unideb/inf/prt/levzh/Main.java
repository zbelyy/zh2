package hu.unideb.inf.prt.levzh;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

    private static JFrame frame = new JFrame("5. feladat");

    private static JTextField személyId;
    private static JTextField kiállításNév;
    private static JTextArea result;

    private static SzemélyDAOImpl személyDAO = new SzemélyDAOImpl("people.xml");
    private static MúzeumDAOImpl múzeumDAO = new MúzeumDAOImpl("museums.xml");
    private static MúzeumServiceImpl múzeumService = new MúzeumServiceImpl();

    public final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container contentPane) {
        if (RIGHT_TO_LEFT) {
            contentPane.setComponentOrientation(
                    ComponentOrientation.RIGHT_TO_LEFT);
        }
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        contentPane.add(new JLabel("Személy azonosító:"), c);
        c.gridx = 1;
        c.gridy = 0;
        személyId = new JTextField();
        contentPane.add(személyId, c);
        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(new JLabel("Kiállítás neve:"), c);
        c.gridx = 1;
        c.gridy = 1;
        kiállításNév = new JTextField();
        contentPane.add(kiállításNév, c);
        c.gridx = 0;
        c.gridy = 2;
        JButton jegyár = new JButton("Jegyár számítása");
        jegyár.setActionCommand("JEGYAR");
        jegyár.addActionListener(new ButtonClickListener());
        contentPane.add(jegyár, c);
        c.gridx = 1;
        c.gridy = 2;
        JButton lehetőségek = new JButton("Lehetőségek");
        lehetőségek.setActionCommand("LEHETOSEGEK");
        lehetőségek.addActionListener(new ButtonClickListener());
        contentPane.add(lehetőségek, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        result = new JTextArea(15, 30);
        result.setEditable(false);
        contentPane.add(result, c);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("5. feladat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private static class ButtonClickListener implements ActionListener {

        public ButtonClickListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("JEGYAR")) {
                //jegyár számítás
                String id = személyId.getText();
                try {
                    Személy személy = személyDAO.getSzemélyById(id);

                    String név = kiállításNév.getText();
                    Kiállítás kiállítás = múzeumDAO.getKiállításByNév(név);

                    int jegyár = 0;
                    if (kiállítás != null && személy != null) {
                        jegyár = múzeumService.getJegyár(kiállítás, személy);
                    }
                    result.setText("" + jegyár);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Hibás bemenet!", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (command.equals("LEHETOSEGEK")) {
                //lehetőségek
                try {
                    // TODO
                    //itt több ponton hiányosnak érzem a dolgot
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Hibás bemenet!", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
