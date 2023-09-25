package rkoepplinger.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rkoepplinger.model.Rechtschreibtrainer;
import rkoepplinger.model.WortEintrag;
import rkoepplinger.model.WortListe;

import static org.junit.jupiter.api.Assertions.*;

public class TestRechtschreibtrainerFeature {
    @Test
    @DisplayName("01 mehrere richtige Wörter und richtige URLs hinzufügen.")
    void neueWortEintraegeInListe() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        assertEquals("Katze, https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg\nHund, https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg\n",liste.toString());
    }
    @Test
    @DisplayName("02 falsches Wort und falsche URL hinzufügen.")
    void neuerFalscherWortEintragInListe() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            liste.wortHinzufuegen(null, "hps://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        } );
        assertTrue(e1.getMessage().startsWith("Kein gültiges Wort"));
    }
    @Test
    @DisplayName("03 Wort aus Liste richtig bekommen")
    void wortAusListeBekommen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        WortEintrag w1 = liste.getWort(0);
        assertEquals("Katze",w1.getWort());
    }
    @Test
    @DisplayName("04 Wort aus Liste mittels zu niedrigem Index versuchen zu bekommen, aber stattdessen IndexOutOfBoundsException")
    void wortAusListemitZuNiedrigemIndexNichtBekommen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            WortEintrag w1 = liste.getWort(-1);
        } );
        assertTrue(e1 instanceof IndexOutOfBoundsException);
    }
    @Test
    @DisplayName("05 Wort aus Liste mittels zu hohem Index versuchen zu bekommen, aber stattdessen IndexOutOfBoundsException")
    void wortAusListemitZuHohemIndexNichtBekommen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            WortEintrag w1 = liste.getWort(2);
        } );
        assertTrue(e1 instanceof IndexOutOfBoundsException);
    }
    @Test
    @DisplayName("06 Wort mit richtigem Wort Löschen")
    void wortAusListeLoeschen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Boolean success = liste.loescheWort("Katze");
        assertEquals(true, success);
    }
    @Test
    @DisplayName("07 Wort mit nicht vorhandenen Wort Löschen")
    void wortAusListeLoeschenMitNichtVorhandenenWort() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Boolean success = liste.loescheWort("Maus");
        assertEquals(false, success);
    }
    @Test
    @DisplayName("08 Wort mit falschem Wort Löschen")
    void wortAusListeLoeschenMitFalschemWort() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            liste.loescheWort(null);
        } );
        assertTrue(e1.getMessage().startsWith("Kein gültiges Wort"));
    }
    @Test
    @DisplayName("09 WortEintrag zufällig auswählen")
    void wortEintragZufaelligAuswaehlen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenZufall();
        WortEintrag w1 = r1.getAktuell();
        assertTrue(w1.getWort().equals("Katze") || w1.getWort().equals("Hund"));
    }
    @Test
    @DisplayName("10 WortEintrag zufällig auswählen bei leerer Liste")
    void wortEintragZufaelligAuswaehlenBeiLeererListe() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            r1.auswaehlenZufall();
        } );
        assertTrue(e1.getMessage().startsWith("Liste ist leer"));
    }
    @Test
    @DisplayName("11 WortEintrag mit Index auswählen")
    void wortEintragMitIndexAuswaehlen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        WortEintrag w1 = r1.getAktuell();
        assertTrue(w1.getWort().equals("Katze"));
    }
    @Test
    @DisplayName("12 WortEintrag mittels Index auswählen bei leerer Liste")
    void wortEintragMittelsIndexAuswaehlenBeiLeererListe() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            r1.auswaehlenIndex(0);
        } );
        assertTrue(e1.getMessage().startsWith("Liste ist leer"));
    }
    @Test
    @DisplayName("13 WortEintrag mittels zu hohem Index auswählen")
    void wortEintragMittelsFalschemIndexAuswaehlen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            r1.auswaehlenIndex(2);
        } );
        assertTrue(e1 instanceof IndexOutOfBoundsException);
    }
    @Test
    @DisplayName("14 WortEintrag mittels zu niedrigem Index auswählen")
    void wortEintragMittelsNiedrigemIndexAuswaehlen() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> {
            r1.auswaehlenIndex(-1);
        } );
        assertTrue(e1 instanceof IndexOutOfBoundsException);
    }
    @Test
    @DisplayName("15 Wort mit check richtig übergeben")
    void wortCheckRichtig() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(true, r1.check("Katze"));
    }
    @Test
    @DisplayName("16 Wort mit check falsch übergeben")
    void wortCheckFalsch() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(false, r1.check("Kaze"));
    }
    @Test
    @DisplayName("17 Wort mit check falsch mit Kleinschreibung übergeben")
    void wortCheckMitKleinschreibung() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(false, r1.check("katze"));
    }
    @Test
    @DisplayName("18 Wort mit checkIgnoreCase richtig übergeben")
    void wortCheckIgnoreCaseRichtig() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(true, r1.checkIgnoreCase("Katze"));
    }
    @Test
    @DisplayName("19 Wort mit checkIgnoreCase falsch übergeben")
    void wortCheckIgnoreCaseFalsch() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(false, r1.checkIgnoreCase("Kaze"));
    }
    @Test
    @DisplayName("20 Wort mit checkIgnoreCase falsch mit Kleinschreibung übergeben")
    void wortCheckIgnoreCaseMitKleinschreibung() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        assertEquals(true, r1.checkIgnoreCase("katze"));
    }
    @Test
    @DisplayName("21 Statistik bei richtigem Wort")
    void statistikRichtig() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        r1.check("Katze");
        assertTrue(r1.getStatistikRichtig() == 1 && r1.getStatistikGesamt() == 1);
    }
    @Test
    @DisplayName("22 Statistik bei falschem Wort")
    void statistikFalsch() {
        WortListe liste = new WortListe();
        Rechtschreibtrainer r1 = new Rechtschreibtrainer(liste);
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1.auswaehlenIndex(0);
        r1.check("Kaze");
        assertTrue(r1.getStatistikRichtig() == 0 && r1.getStatistikGesamt() == 1);
    }
}
