package rkoepplinger.view;
import java.awt.*;
import java.net.URL;

import javax.swing.*;

import rkoepplinger.control.WortControl;
/**
 * Eine GrafikPanel Klasse, die das Design für den Worttrainer erstellt.
 * @author Robin Köpplinger
 * @version 2023-10-01
 */
public class WortPanel extends JPanel{
    //5 Attribute
    private JButton buttons[];
    private JLabel labels[];
    private JTextField textfeld;
    private JLabel imageLabel;
    private WortControl control;
    /**
     * Ein WortPanel Konstruktor
     * @param control Das übergebene WortControl Objekt, dass später
     * f�r die Ereignissteuerung benötigt wird
     */
    public WortPanel(WortControl control) {
        //control wird definiert
        this.control = control;
        //Das Layout wird festgelegt
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        //Labels
        labels = new JLabel[5];
        labels[0] = new JLabel("Welches Wort wird im Bild dargestellt?", SwingConstants.CENTER);
        labels[1] = new JLabel("Richtige Woerter:", SwingConstants.LEFT);
        labels[2] = new JLabel("0", SwingConstants.CENTER);
        labels[3] = new JLabel("Anzahl Woerter:", SwingConstants.LEFT);
        labels[4] = new JLabel("0", SwingConstants.CENTER);
        for(int i=0; i<5; i++) {
            labels[i].setOpaque(true);
            labels[i].setBackground(new Color(200, 200, 200));
            labels[i].setPreferredSize(new Dimension(60, 60));
        }
        //Buttons
        buttons = new JButton[2];
        buttons[0] = new JButton("Zuruecksetzen");
        buttons[0].setActionCommand("Zurücksetzen");
        buttons[1] = new JButton("Wort hinzufuegen");
        buttons[1].setActionCommand("Wort hinzufügen");
        for(int i=0; i<2; i++) {
            this.buttons[i].addActionListener(this.control);
        }
        // Bild
        imageLabel = new JLabel(new ImageIcon(), SwingConstants.CENTER);
        //Textfeld
        textfeld = new JTextField();
        this.textfeld.addActionListener(this.control);
        //Layouts
        JPanel oben = new JPanel(new GridLayout(2,1));
        JPanel unten = new JPanel(new GridLayout(2,1));
        JPanel unten1 = new JPanel(new GridLayout(1,3));
        JPanel unten2 = new JPanel(new GridLayout(1,3));
        //Hinzuf�gen der GUI-Komponenten
        oben.add(labels[0]);
        oben.add(textfeld);
        unten1.add(labels[1]);
        unten1.add(labels[2]);
        unten1.add(buttons[0]);
        unten2.add(labels[3]);
        unten2.add(labels[4]);
        unten2.add(buttons[1]);
        this.add(oben,BorderLayout.PAGE_START);
        this.add(imageLabel, BorderLayout.CENTER);
        unten.add(unten1);
        unten.add(unten2);
        this.add(unten,BorderLayout.PAGE_END);
    }
    /**
     * Gibt den Text aus dem Textfeld zurück
     * @return Der Text
     */
    public String getTextFromTextField() {
        return textfeld.getText();
    }
    /**
     * Die Statistik wird aktualisiert
     * @param statistikGesamt Die Anzahl Wörter
     * @param statistikRichtig Die Anzahl der richtigen Wörter
     */
    public void statistikAktualisieren(int statistikGesamt, int statistikRichtig) {
        labels[2].setText("" + statistikRichtig);
        labels[4].setText("" + statistikGesamt);
    }
    /**
     * Diese Methode ändert das Bild auf das neue Bild
     * mit der Hilfe der übergebenen URL
     * @param url Die URL
     */
    public void bildAendern(URL url) {
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage();
        //Gr��e anpassen
        float verhaeltnis = (float)this.getHeight()/1.6f/(float)image.getHeight(this);
        float width = (float)image.getWidth(this)*verhaeltnis;
        float height = (float)this.getHeight()/1.6f;
        image = image.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
        //Label aktualisieren
        imageLabel.setIcon(new ImageIcon(image));
    }
}
