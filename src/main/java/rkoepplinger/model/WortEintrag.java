package rkoepplinger.model;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Eine Klasse, die ein Wort und eine zugehörige URL speichert
 * @author Robin Köpplinger
 * @version 2023-09-24
 */
public class WortEintrag {
    //2 Attribute
    private String wort = "";
    private String url = "";
    /**
     * Ein WortEintrag Konstruktor
     * @param wort Das Wort
     * @param url Die URL
     * @throws IllegalArgumentException Wenn �bergebene Werte nicht stimmen
     */
    public WortEintrag(String wort, String url) throws IllegalArgumentException {
        setWort(wort);
        setUrl(url);
    }
    /**
     * �berpr�ft die Korrektheit einer URL
     * @param url Die �bergebene URL
     * @return true oder false
     */
    public static boolean checkURL(String url) {
        String[] schemes = {"http", "https", "ftp"};
        UrlValidator validator = new UrlValidator(schemes);
        return validator.isValid(url);
    }
    /**
     * Gibt wort zurück
     * @return wort
     */
    public String getWort() {
        return wort;
    }
    /**
     * Gibt url zur�ck
     * @return url
     */
    public String getUrl() {
        return url;
    }
    /**
     * Pr�ft das übergebene wort und setzt ihn wenn er passt
     * @param wort Das übergebene wort
     * @throws IllegalArgumentException Wirft eine Exception, wenn der Wert nicht passt
     */
    public void setWort(String wort) throws IllegalArgumentException {
        if(wort == null) {
            IllegalArgumentException e1 = new IllegalArgumentException("Kein gültiges Wort");
            throw e1;
        }
        if(wort.length() >= 2) {
            this.wort = wort;
        }else {
            IllegalArgumentException e1 = new IllegalArgumentException("Kein gültiges Wort");
            throw e1;
        }
    }
    /**
     * Pr�ft die �bergebene url und setzt ihn wenn er passt
     * @param url Das �bergebene url
     * @throws IllegalArgumentException Wirft eine Exception, wenn der Wert nicht passt
     */
    public void setUrl(String url) throws IllegalArgumentException{
        if(WortEintrag.checkURL(url) == true) {
            this.url = url;
        }else {
            IllegalArgumentException e1 = new IllegalArgumentException("Keine gültige URL");
            throw e1;
        }
    }
    /**
     * �berschreibt die toString Methode der Klasse Object, die das
     * Wort und die Url zurückgeben.
     */
    @Override
    public String toString() {
        return wort + ", " + url;
    }
}
