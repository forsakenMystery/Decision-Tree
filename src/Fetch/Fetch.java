/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fetch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author forsakenMystery
 */
public class Fetch {

    public static List<String> getSymptoms() throws FileNotFoundException, IOException {
        ArrayList<String> symptoms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\symptoms.soz"))) {
            String line = br.readLine();
            String[] split = line.split(",");
            symptoms.addAll(Arrays.asList(split));
        }
        return symptoms;
    }
    public static List<String> getSymptoms(String address) throws FileNotFoundException, IOException {
        ArrayList<String> symptoms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            String line = br.readLine();
            String[] split = line.split(",");
            symptoms.addAll(Arrays.asList(split));
        }
        return symptoms;
    }

    public static List< Pair<String, Map<String, Boolean>>> getDisease() throws FileNotFoundException, IOException {
        ArrayList<Pair<String, Map<String, Boolean>>> disease = new ArrayList<>();
        List<String> symptoms = getSymptoms();
        System.out.println("symptoms = " + symptoms);
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\forsakenmystery\\Net beans\\DecisionTree\\src\\Fetch\\disease.doz"))) {
            String line = br.readLine();
            while (line != null) {
                LinkedHashMap<String, Boolean> m = new LinkedHashMap<>();
                String t[]=line.split(":");
                String split = t[1];
                for (int i = 0; i < symptoms.size(); i++) {
                    m.put(symptoms.get(i), split.charAt(i) == 'T');
                }
                Pair<String,Map<String,Boolean>> s=new Pair<>(t[0],m);
                disease.add(s);
                line = br.readLine();
            }
        }
        return disease;
    }
    public static List< Pair<String, Map<String, Boolean>>> getDisease(String address) throws FileNotFoundException, IOException {
        ArrayList<Pair<String, Map<String, Boolean>>> disease = new ArrayList<>();
        List<String> symptoms = getSymptoms();
        System.out.println("symptoms = " + symptoms);
        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            String line = br.readLine();
            while (line != null) {
                LinkedHashMap<String, Boolean> m = new LinkedHashMap<>();
                String t[]=line.split(":");
                String split = t[1];
                for (int i = 0; i < symptoms.size(); i++) {
                    m.put(symptoms.get(i), split.charAt(i) == 'T');
                }
                Pair<String,Map<String,Boolean>> s=new Pair<>(t[0],m);
                disease.add(s);
                line = br.readLine();
            }
        }
        return disease;
    }
    
    public static void main(String[] args) throws IOException {
        List<Pair<String, Map<String, Boolean>>> disease = getDisease();
        System.out.println("disease = " + disease);
    }
}
