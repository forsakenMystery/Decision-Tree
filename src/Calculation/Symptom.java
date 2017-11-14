/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author forsakenMystery
 */
public class Symptom {

    public static List<Symptom> getAllSymptoms(String addressSymptom) {
        ArrayList<Symptom> s = null;
        try {
            List<String> symptoms = Fetch.Fetch.getSymptoms(addressSymptom);
            s = new ArrayList<>();
            for (int i = 0; i < symptoms.size(); i++) {
                s.add(new Symptom(symptoms.get(i)));
            }
            setAllSymptoms(s);
        } catch (IOException ex) {
            Logger.getLogger(Symptom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public Symptom(String name) {
        this.name = name;
    }

    /**
     * @return the allSymptoms
     */
    public static List<Symptom> getAllSymptoms() {
        return allSymptoms;
    }

    /**
     * @param aAllSymptoms the allSymptoms to set
     */
    private static void setAllSymptoms(List<Symptom> aAllSymptoms) {
        allSymptoms = aAllSymptoms;
    }
    private String name;
    private static List<Symptom> allSymptoms;

    static {
        try {
            List<String> symptoms = Fetch.Fetch.getSymptoms();
            ArrayList<Symptom> s = new ArrayList<>();
            for (int i = 0; i < symptoms.size(); i++) {
                s.add(new Symptom(symptoms.get(i)));
            }
            setAllSymptoms(s);
        } catch (IOException ex) {
            Logger.getLogger(Symptom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Symptom) {
            Symptom sym = (Symptom) obj;
            return sym.name.equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        return this.getName(); //To change body of generated methods, choose Tools | Templates.
    }

}
