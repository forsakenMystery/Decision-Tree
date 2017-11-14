/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.util.Pair;

/**
 *
 * @author forsakenMystery
 */
public class Disease {

    public static List<Disease> getDisease(String addressDisease) throws IOException {
        diseases=null;
        if (diseases == null) {
            ArrayList<Disease> ans = new ArrayList<>();
            List<Pair<String, Map<String, Boolean>>> disease = Fetch.Fetch.getDisease(addressDisease);
            disease.forEach((Pair<String, Map<String, Boolean>> t) -> {
                ArrayList<Symptom> tr = new ArrayList<>();
                t.getValue().forEach((String t1, Boolean u) -> {
                    if (u) {
                        tr.add(new Symptom(t1));
                    }
                });
                ans.add(new Disease(t.getKey(), tr));
            });
            diseases = ans;
        }
        return diseases;
    }

    private String name;
    private ArrayList<Symptom> symptoms;
    private static List<Disease> diseases = null;

    private Disease(String name, ArrayList<Symptom> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }

    public static List<Disease> getDisease() throws IOException {
        if (diseases == null) {
            ArrayList<Disease> ans = new ArrayList<>();
            List<Pair<String, Map<String, Boolean>>> disease = Fetch.Fetch.getDisease();
            disease.forEach((Pair<String, Map<String, Boolean>> t) -> {
                ArrayList<Symptom> tr = new ArrayList<>();
                t.getValue().forEach((String t1, Boolean u) -> {
                    if (u) {
                        tr.add(new Symptom(t1));
                    }
                });
                ans.add(new Disease(t.getKey(), tr));
            });
            diseases = ans;
        }
        return diseases;
    }
    

    /**
     * @return the symptoms
     */
    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    /**
     * @param symptoms the symptoms to set
     */
    public void setSymptoms(ArrayList<Symptom> symptoms) {
        this.symptoms = symptoms;
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
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.symptoms);
        return hash;
    }

    @Override
    public String toString() {
        return this.getName()+" : "+this.symptoms+"\n"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public static void main(String[] args) throws IOException {
        List<Disease> disease = getDisease();
        System.out.println("disease = " + disease);
    }

}
