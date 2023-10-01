package rkoepplinger.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rkoepplinger.model.Persistierung;
import rkoepplinger.model.PersistierungJSON;
import rkoepplinger.model.Rechtschreibtrainer;
import rkoepplinger.model.WortListe;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPersistierung {
    private WortListe liste;
    private Rechtschreibtrainer r1;
    private Persistierung p1;
    private Rechtschreibtrainer r2;
    @BeforeEach
    void beforeEach() {
        liste = new WortListe();
        liste.wortHinzufuegen("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        liste.wortHinzufuegen("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg");
        r1 = new Rechtschreibtrainer(liste);
        r1.auswaehlenIndex(0);
        r1.check("katze");
        p1 = new PersistierungJSON(r1);
        p1.speichern();
        p1.laden();
        r2 = p1.getTrainer();
    }
    @Test
    @DisplayName("01 aktuelles Wort richtig wiederhergestellt")
    void aktuellesWort() {
        assertEquals("Katze", r2.getAktuell().getWort());
    }
    @Test
    @DisplayName("02 aktuelle URL richtig wiederhergestellt")
    void aktuelleURL() {
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg", r2.getAktuell().getUrl());
    }
    @Test
    @DisplayName("03 statistikGesamt richtig wiederhergestellt")
    void statistikGesamt() {
        assertEquals(1,r2.getStatistikGesamt());
    }
    @Test
    @DisplayName("04 statistikRichtig korrekt wiederhergestellt")
    void statistikRichtig() {
        assertEquals(0,r2.getStatistikRichtig());
    }
    @Test
    @DisplayName("05 liste richtig wiederhergestellt")
    void wortPaare() {
        assertEquals("Katze, https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg\nHund, https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg\n",r2.getListe().toString());
    }
}
