package rkoepplinger.model;

import java.util.LinkedList;

/**
 * Eine Kasse zur Erstellung einer Liste an WortEinträgen
 * @author Robin Köpplinger
 * @version 2023-09-24
 */
public class WortListe {
    //1 Attribut
    private LinkedList<WortEintrag> wortPaare;
    /**
     * Ein WortListe Konstruktor, der eine Liste erstellt.
     */
    public WortListe(){
        wortPaare = new LinkedList<WortEintrag>();
    }
    /**
     * Erstellt ein Wort aus den übergebenen Werten und fügt es dann dem Array hinzu
     * @param wort Das Wort
     * @param url Die URL
     * @throws IllegalArgumentException Wenn übergebene Werte nicht stimmen
     */
    public void wortHinzufuegen(String wort, String url) throws IllegalArgumentException {
        WortEintrag we = new WortEintrag(wort, url);
        wortPaare.addLast(we);
    }
    /**
     * Gibt den WortEintrag aus der Liste mit der Hilfe des Indexes zur�ck
     * @param index Der Index
     * @return Gibt den gefundenen WortEintrag zur�ck
     * @throws IndexOutOfBoundsException Wenn es kein passender Index ist
     */
    public WortEintrag getWort(int index) throws IndexOutOfBoundsException {
        if(index < wortPaare.size() && index > 0) {
            return wortPaare.get(index);
        }else {
            IndexOutOfBoundsException e1 = new IndexOutOfBoundsException();
            throw e1;
        }
    }
    /**
     * L�scht das gesuchte Wort aus der Liste
     * @param wort Das gesuchte Wort
     * @return true oder false, ob das Wort gel�scht wurde
     * @throws IllegalArgumentException Wenn der �bergebene Wert kein g�ltiges Wort ist
     */
    public boolean loescheWort(String wort) throws IllegalArgumentException{
        //Prüfung, ob der übergebene Wert stimmen kann
        if(wort.length() < 2) {
            IllegalArgumentException e1 = new IllegalArgumentException("Kein gültiges Wort");
            throw e1;
        }
        if(wortPaare.indexOf(wort) != -1) {
            wortPaare.remove(wort);
            return true;
        }else {
            return false;
        }
    }
    /**
     * �berschreibt die toString Methode der Klasse WortEintrag, die die
     * Inhalte der WortEintr�ge zur�ckgibt.
     */
    @Override
    public String toString() {
        String text = "";
        for (WortEintrag we : wortPaare) {
            text += we.toString() + "\n";
        }
        return text;
    }
    /**
     * Gibt die Anzahl an gespeicherten W�rter zur�ck
     * @return die Anzahl
     */
    public int getAnzahlWoerter() {
        return wortPaare.size();
    }
}
