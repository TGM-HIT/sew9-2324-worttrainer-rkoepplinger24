package rkoepplinger.model;
import java.util.Random;
/**
 * Eine WortTrainer Klasse, die den Benutzer trainiert
 * @author Robin K�pplinger
 * @version 2023-09-24
 */
public class Rechtschreibtrainer {
    //4 Attribute
    private WortListe liste;
    private int ausgewaehlt;
    private int statistikGesamt;
    private int statistikRichtig;
    /**
     * Ein WortTrainer Konstruktor
     * @param liste Die WortListe
     * @throws IllegalArgumentException Wenn der �bergebene Wert nicht stimmt
     */
    public Rechtschreibtrainer(WortListe liste) throws IllegalArgumentException{
        this.liste = liste;
    }
    /**
     * Wählt ein zufälliges neues Wort aus der Liste aus
     * @throws IllegalArgumentException Falls die Liste leer ist.
     */
    public void auswaehlenZufall() throws IllegalArgumentException{
        if(liste.getAnzahlWoerter() == 0) {
            throw new IllegalArgumentException("Liste ist leer");
        }
        Random r1 = new Random();
        ausgewaehlt = r1.nextInt(liste.getAnzahlWoerter());
    }

    /**
     * Wählt mittels des Indexes das nächste Wort aus.
     * @param index Der Index
     * @throws IllegalArgumentException Falls die Liste leer ist.
     * @throws IndexOutOfBoundsException Falls der Index nicht passt.
     */
    public void auswaehlenIndex(int index) throws IndexOutOfBoundsException, IllegalArgumentException{
        if(liste.getAnzahlWoerter() == 0) {
            throw new IllegalArgumentException("Liste ist leer");
        }
        if(index < 0 || index >= liste.getAnzahlWoerter()) {
            throw new IndexOutOfBoundsException();
        }else {
            ausgewaehlt = index;
        }
    }
    /**
     * Gibt den aktuellen WortEintrag zurück
     * @return Der WortEintrag
     */
    public WortEintrag getAktuell() {
        return liste.getWort(ausgewaehlt);
    }
    /**
     * �berpr�ft ob das �bergebene Wort mit dem aktuellem Wort �bereinstimmt und ber�cksichtigt
     * dabei Gro� und Kleinschreibung. Au�erdem aktualisiert es die Statistik
     * @param wort Das �bergebene Wort
     * @return Ob es rcihtig oder falsch ist
     */
    public boolean check(String wort) {
        statistikGesamt++;
        if(wort.equals(liste.getWort(ausgewaehlt).getWort())) {
            statistikRichtig++;
            return true;
        }
        return false;
    }
    /**
     * �berpr�ft ob das �bergebene Wort mit dem aktuellem Wort �bereinstimmt und ber�cksichtigt
     * dabei nicht die Gro� und Kleinschreibung. Au�erdem aktualisiert es die Statistik
     * @param wort Das �bergebene Wort
     * @return Ob es rcihtig oder falsch ist
     */
    public boolean checkIgnoreCase(String wort) {
        statistikGesamt++;
        if(wort.toLowerCase().equals(liste.getWort(ausgewaehlt).getWort().toLowerCase())) {
            statistikRichtig++;
            return true;
        }
        return false;
    }
    /**
     * Gibt den Gesamtwert der Statistik zur�ck
     * @return der Gesamtwert
     */
    public int getStatistikGesamt() {
        return statistikGesamt;
    }
    /**
     * Gibt die Anzahl richtiger Angaben der Statistik zur�ck
     * @return die richtigen Angaben
     */
    public int getStatistikRichtig() {
        return statistikRichtig;
    }
    /**
     * Gibt die Liste der WortEintr�ge zur�ck
     * @return liste die Liste
     */
    public WortListe getListe() {
        return liste;
    }
    /**
     * Setzt den Wert von statistikGesamt
     * @param zahl der neue Wert
     */
    public void setStatistikGesamt(int zahl) {
        this.statistikGesamt = zahl;
    }
    /**
     * Setzt den Wert von statistikRichtig
     * @param zahl der neue Wert
     */
    public void setStatistikRichtig(int zahl) {
        this.statistikRichtig = zahl;
    }
    /**
     * �berschrieben wird die toString Methode der Klasse Object
     * Es werden die toString Methoden der anderen beiden Klassen
     * zur Hilfe genommen
     */
    @Override
    public String toString() {
        String text = statistikGesamt + ", " + statistikRichtig +"\n";
        text += liste.toString();
        return text;
    }
    public void setListe(WortListe liste) {
        this.liste = liste;
    }
}