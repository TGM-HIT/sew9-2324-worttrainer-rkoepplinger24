package rkoepplinger.control;

import rkoepplinger.view.WortFrame;

import rkoepplinger.view.WortPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import rkoepplinger.model.WortEintrag;
import rkoepplinger.model.WortListe;
import rkoepplinger.model.Rechtschreibtrainer;
import rkoepplinger.model.Persistierung;
import rkoepplinger.model.PersistierungJSON;
/**
 * Die Controller Klasse, die die Modell und View Klassen
 * vom Worttrainer steuert.
 * @author Robin Köpplinger
 * @version 2023-10-01
 */
public class WortControl implements ActionListener {
    private WortPanel panel;
    private WortFrame frame;
    private Rechtschreibtrainer trainer;
    private Persistierung persistierung;
    public WortControl() {
        //model
        trainer = new Rechtschreibtrainer(new WortListe());
        persistierung = new PersistierungJSON(trainer);
        persistierung.laden();
        trainer = persistierung.getTrainer();
        //view
        panel = new WortPanel(this);
        frame = new WortFrame(panel);
        panel.statistikAktualisieren(trainer.getStatistikGesamt(), trainer.getStatistikRichtig());
        this.wortAnzeigen();
    }
    /**
     * Die Methode aus dem Interface für die Ereignissteuerung
     * @param e Das ActionEvent Objekt
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Wenn die Buttons gedrückt wurden
        if(e.getActionCommand().equals("Zurücksetzen")) {
            this.zuruecksetzen();
            persistierung.speichern();
        }else if(e.getActionCommand().equals("Wort hinzufügen")) {
            this.wortHinzufuegen();
            persistierung.speichern();
        }else {
            this.ueberpruefen();
            persistierung.speichern();
        }
    }
    public static void main(String[] args) {
        WortControl control = new WortControl();
    }
    /**
     * F�gt ein Wort hinzu und erstellt auch ein WortTrainer
     * wenn bisher noch keiner erstellt wurde
     */
    public void wortHinzufuegen() {
        //Benutzereingaben
        String wort = JOptionPane.showInputDialog(null, "Geben Sie das Wort ein: ");
        String url = JOptionPane.showInputDialog(null, "Geben Sie die URL ein: ");
        if(wort != null && url != null) {
            //Wenn noch keine WortTrainer existiert
            if(trainer == null) {
                WortListe liste = null;
                try {
                    liste = new WortListe();
                    liste.wortHinzufuegen(wort, url);
                }catch(IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
                if(liste != null) {
                    trainer = new Rechtschreibtrainer(liste);
                    this.wortAnzeigen();
                }
                //Wenn schon ein WortTrainer existiert
            }else {
                WortListe liste = trainer.getListe();
                try {
                    liste.wortHinzufuegen(wort, url);
                }catch(IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                    liste = null;
                }
                if(liste != null) {
                    trainer.setListe(liste);
                }
            }
        }
    }
    /**
     * Bestimmt das neue Wort und übergibt der Panel-Klasse die URL
     * Wenn sie nicht gefunden wurde gibt sie eine Fehlermeldung aus
     * und löscht es aus der Liste
     */
    public void wortAnzeigen() {
        if(trainer != null) {
            trainer.auswaehlenZufall();
            WortEintrag zufall = trainer.getAktuell();
                    URL url = null;
            try {
                url = new URL(zufall.getUrl());
            }catch(MalformedURLException e1) {
                //Wenn die URL nicht gefunden wurde
                JOptionPane.showMessageDialog(null, "URL nicht gefunden: " + e1.getMessage());
                try{
                    WortListe liste = trainer.getListe();
                    liste.loescheWort(trainer.getAktuell().getWort());
                }catch (IllegalArgumentException e2) {
                    trainer = null;
                }
            }
            if(url != null) panel.bildAendern(url);
        }
    }
    /**
     * überprüft das eingegebene Wort und vergleicht es mit dem aktuellen Wort
     */
    public void ueberpruefen() {
        //Das eingegebene Wort
        String wort = panel.getTextFromTextField();
        //Wenn alles richtig ist wird auch gleich das n�chste Wort geladen
        if(trainer.check(wort) == true) {
            JOptionPane.showMessageDialog(null, "Das Wort ist richtig geschrieben");
            this.wortAnzeigen();
        }else {
            trainer.setStatistikGesamt(trainer.getStatistikGesamt()-1);
            //Wenn nur die Gro�-/Kleinschreibung nicht passt
            if(trainer.checkIgnoreCase(wort) == true) {
                trainer.setStatistikRichtig(trainer.getStatistikRichtig()-1);
                JOptionPane.showMessageDialog(null, "Die Gro\\u00df-/Kleinschreibung ist falsch");
            }else {
                //Wenn es gar nicht passt
                JOptionPane.showMessageDialog(null, "Das Wort ist falsch");
            }
        }
        //Aktualisieren der Statistik
        panel.statistikAktualisieren(trainer.getStatistikGesamt(), trainer.getStatistikRichtig());
    }
    /**
     * Setzt die Statistik des aktuellen Worttrainers zur�ck
     */
    public void zuruecksetzen() {
        trainer.setStatistikGesamt(0);
        trainer.setStatistikRichtig(0);
        panel.statistikAktualisieren(0, 0);
    }
}
