package rkoepplinger.model;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

public class PersistierungJSON extends Persistierung{
    /**
     * Ein PersistierungsJSON-Konstruktor, der die Persistierung mit JSON ermöglicht.
     * @param trainer Der Rechtschreibtrainer für die Persistierung.
     */
    public PersistierungJSON(Rechtschreibtrainer trainer) {
        super(trainer);
    }

    /**
     * Speichert die aktuelle Worttrainer-Session.
     */
    @Override
    public void speichern() {
        JSONObject json = new JSONObject(trainer);
        FileWriter fw = null;
        try {
            fw = new FileWriter("persistierung.json");
            fw.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /**
     * Lädt die aktuellste Worttrainer-Session.
     */
    @Override
    public void laden() {
        try {
            Reader input = new FileReader("persistierung.json");
            Gson gson = new Gson();
            Type type = new TypeToken<Rechtschreibtrainer>(){}.getType();
            trainer = gson.fromJson(input, type);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
