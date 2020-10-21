package sequenties;

/**
 * JavaDoc commentaar
 *
 * @author door Jasper
 * @werking Gui die een bestand inporteert en kijkt of dit DNA, RNA een peptide of geen van allen is
 * @werking daarna visualiseerd de nucleotide of de aminozuren.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Gui {

    private JPanel panel1;
    private JLabel uitleg;
    private JTextField geefBestandTextField;
    private JButton bladerButton;
    private JTextArea textArea1;
    private JButton visualiseerButton;
    static final String[] ONE = {"R", "N", "D", "C", "Q", "E", "G", "H", "K", "S", "T", "Y",
            "A", "F", "I", "L", "M", "P", "W", "V"};
    private JPanel tekenpanel;


    public static void main(String[] args) {
        /**
         * frame settings
         */
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);


    }


    public Gui() {
        /**
         * tekenpanel visualiseren
         */
        tekenpanel = new JPanel();
        tekenpanel.setPreferredSize(new Dimension(500, 100));
        tekenpanel.setBackground(Color.white);
        panel1.add(tekenpanel);
        tekenpanel.setVisible(true);


        bladerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * @fileChooser openen van bestanden met behulp van fileChooser
                 */
                File selectedFile;
                JFileChooser fileChooser = new JFileChooser();
                int reply = fileChooser.showOpenDialog(null);
                if (reply == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    geefBestandTextField.setText(((File) selectedFile).getAbsolutePath());
                }


            }
        });
        visualiseerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * @check DNA, RNA of peptide check en visualisatie
                 */
                int jeiwit = 0;
                int jDNA = 0;
                int jRNA = 0;
                String seq = null;

                String line;
                int t = 0;

                try {
                    BufferedReader inFile = new BufferedReader(new FileReader(geefBestandTextField.getText()));
                    while ((line = inFile.readLine()) != null) {
                        if (seq == null) {
                            seq = line.toString();
                        } else {
                            seq += line.toString();
                        }
                        for (int i = 0; i < line.length(); i++) {
                            t++;

                            char a = line.charAt(i);
                            System.out.println(a);
                            if (a == 'A' || a == 'G' || a == 'T' || a == 'C') {
                                jDNA++;
                            }
                            if (a == 'A' || a == 'G' || a == 'U' || a == 'C') {
                                jRNA++;
                            }
                            for (int x = 0; x < ONE.length; x++) {
                                char s = ONE[x].charAt(0);
                                if (a == s) {
                                    jeiwit++;
                                    if (x > 11) {
                                    } else {
                                    }
                                }

                            }
                        }
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                if (jDNA == t) {
                    textArea1.setText("DNA: " + seq);
                    DNA d1 = new DNA();
                    d1.setgcpers(seq);
                    System.out.println(d1.getgcpers());

                    Graphics tekenveld = tekenpanel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        d1.setcolor(a);
                        tekenveld.setColor(d1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }
                } else if (jRNA == t) {
                    textArea1.setText("RNA: " + seq);
                    RNA r1 = new RNA();
                    Graphics tekenveld = tekenpanel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        r1.setcolor(a);
                        tekenveld.setColor(r1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }
                } else if (jeiwit == t) {
                    textArea1.setText("eiwit: " + seq);
                    Peptide p1 = new Peptide();
                    Graphics tekenveld = tekenpanel.getGraphics();
                    int afstand = 50;
                    for (int i = 0; i < seq.length(); i++) {
                        char a = seq.charAt(i);
                        p1.setcolor(a);
                        tekenveld.setColor(p1.getColor());
                        tekenveld.drawRect(afstand, 50, 10, 20);
                        tekenveld.fillRect(afstand, 50, 10, 20);
                        afstand += 10;
                    }

                } else {
                    textArea1.setText("geen goede seq");
                }

            }
        });
    }

}

