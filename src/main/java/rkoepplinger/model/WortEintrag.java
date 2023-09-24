package rkoepplinger.model;
/**
 * Eine Klasse, die eine Wort un eine zugeh�rige URL speichert
 * @author Robin Köpplinger
 * @version 2021-09-09
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
        //Sie muss mind. 10 Stellen haben
        if(url.length() >= 10) {
            //Sie muss mit https:// oder http:// beginnen
            String text = url.substring(0, 8);
            if(text.equals("https://")) {
                //Es wird gepr�ft ob nach dem Beginn nur Buchstaben, dann ein Punkt und dann ein Buchstabe kommt
                int zahl = 0;
                for (int i = 8; i<url.length(); i++) {
                    char buchstabe = url.charAt(i);
                    if(buchstabe >= 'A' && buchstabe <= 'Z' || buchstabe >= 'a' && buchstabe <= 'z') {
                        zahl++;
                    }else if(buchstabe == '.' && zahl >= 1) {
                        break;
                    }else {
                        return false;
                    }
                }
                char wert = url.charAt(zahl+9);
                if(wert >= 'A' && wert <= 'Z' || wert >= 'a' && wert <= 'z' ) {
                    return true;
                }
            }else {
                //Sie muss mit https:// oder http:// beginnen
                text = url.substring(0, 7);
                if(text.equals("http://")) {
                    //Es wird gepr�ft ob nach dem Beginn nur Buchstaben, dann ein Punkt und dann ein Buchstabe kommt
                    int zahl = 0;
                    for (int i = 7; i<url.length(); i++) {
                        char buchstabe = url.charAt(i);
                        if(buchstabe >= 'A' && buchstabe <= 'Z' || buchstabe >= 'a' && buchstabe <= 'z') {
                            zahl++;
                        }else if(buchstabe == '.' && zahl >= 1) {
                            break;
                        }else {
                            return false;
                        }
                    }
                    char wert = url.charAt(zahl+8);
                    if(wert >= 'A' && wert <= 'Z' || wert >= 'a' && wert <= 'z' ) {
                        return true;
                    }
                }
            }
        }
        return false;
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
