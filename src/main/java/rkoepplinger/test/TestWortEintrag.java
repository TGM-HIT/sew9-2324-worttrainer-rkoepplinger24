package rkoepplinger.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rkoepplinger.model.WortEintrag;

import static org.junit.jupiter.api.Assertions.*;

public class TestWortEintrag {
    @Test
    @DisplayName("01 echte URL wird gespeichert und richtig zurückgegeben.")
    void echteURLHinzufuegen() {
        WortEintrag w1 = new WortEintrag("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg",w1.getUrl());
    }
    @Test
    @DisplayName("02 falsche URL wird nicht akzeptiert und eine Exception wird geworfen.")
    void falscheURLHinzufuegen() {
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            WortEintrag w1 = new WortEintrag("Katze", "httttps://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        } );
        assertTrue(e1.getMessage().startsWith("Keine gültige URL"));
    }
    @Test
    @DisplayName("03 Wort wird gespeichert und richtig zurückgegeben.")
    void richtigesWortHinzufuegen() {
        WortEintrag w1 = new WortEintrag("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        assertEquals("Katze",w1.getWort());
    }
    @Test
    @DisplayName("04 falsches Wort wird nicht akzeptiert und eine Exception wird geworfen.")
    void falschesWortHinzufuegen() {
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            WortEintrag w1 = new WortEintrag("", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        } );
        assertTrue(e1.getMessage().startsWith("Kein gültiges Wort"));
    }
    @Test
    @DisplayName("05 Nichts wird für Wort übergeben und das wird nicht akzeptiert und eine Exception wird geworfen.")
    void nichtsFuerWortHinzufuegen() {
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> {
            WortEintrag w1 = new WortEintrag(null, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg");
        } );
        assertTrue(e1.getMessage().startsWith("Kein gültiges Wort"));
    }
}
