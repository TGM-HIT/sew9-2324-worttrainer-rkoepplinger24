package rkoepplinger.model;
/**
 * Eine abstrakte Klasse Persistierung, die als Superklasse für das Strategy-Pattern benutzt wird
 * @author Robin Köpplinger
 * @version 2023-10-01
 */
public abstract class Persistierung {
    protected Rechtschreibtrainer trainer;
    public abstract void speichern();
    public abstract void laden();

    /**
     * Gibt den Rechtschreibtrainer zurück
     * @return der Trainer
     */
    public Rechtschreibtrainer getTrainer() {
        return trainer;
    }

    /**
     * Ein Persistierungskonstruktor, der von Subklassen verwendet werden kann und
     * die Persistierungsmethode austauschbar macht.
     * @param trainer
     */
    public Persistierung(Rechtschreibtrainer trainer) {
        this.trainer = trainer;
    }
}
