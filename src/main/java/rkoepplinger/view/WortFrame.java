package rkoepplinger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Eine GrafikFrame Klasse, die mit der Hilfe der Panel Klasse das Design für
 * den Worttrainer erstellt.
 * @author Robin Köpplinger
 * @version 2023-10-01
 */
public class WortFrame extends JFrame{
    /**
     * Ein WortFrame-Konstruktor
     * @param lay Das benutzte PanelLayout
     */
    public WortFrame(JPanel lay) {
        //Der Titel wird festgelegt
        super("Rechtschreibtrainer");
        //Aufruf der geerbten Methoden für das Objekt
        this.add(lay);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1920, 1080);
        this.setVisible(true);
    }
}