/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculation;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author forsakenMystery
 */
public class Entropy {

    private static int yes;
    private static double a;
    private static double b;

    private static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    public static double probablityCk(Disease disease, Symptom symptom, boolean bool) throws IOException {
        if (bool) {
            if (disease.getSymptoms().contains(symptom)) {
                return (double) 1 / numberOfYes(symptom);
            } else {
                return 0;
            }
        } else {
            if (!disease.getSymptoms().contains(symptom)) {
                return (double) 1 / (4 - numberOfYes(symptom));
            } else {
                return 0;
            }
        }
    }

    private static int numberOfYes(Symptom symptom) throws IOException {
        yes = 0;
        Disease.getDisease().forEach((Disease t) -> {
            if (t.getSymptoms().contains(symptom)) {
                yes++;
            }
        });
        return yes;
    }

    public static double getEntropy(List<Disease> diseas,Symptom symptom) throws IOException {
        a = 0;
        b = 0;
        diseas.forEach(new Consumer<Disease>() {

            @Override
            public void accept(Disease t) {
                try {
                    if (probablityCk(t, symptom, true) != 0) {
                        a += (probablityCk(t, symptom, true) * log2(probablityCk(t, symptom, true)));
                    }
                    if (probablityCk(t, symptom, false) != 0) {
                        b += (probablityCk(t, symptom, false) * log2(probablityCk(t, symptom, false)));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Entropy.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
//        System.out.println(numberOfYes(symptom));
        return (((double)(numberOfYes(symptom)*(-a)/4))+((double)(4-numberOfYes(symptom))*(-b)/4));
    }

    public static void main(String[] args) throws IOException {
//        System.out.println("Symptom.getAllSymptoms().get(0) = " + Symptom.getAllSymptoms().get(0));
//        System.out.println("Disease.getDisease().get(0) = " + Disease.getDisease().get(0));
//        double probablityCk = probablityCk(Disease.getDisease().get(0), Symptom.getAllSymptoms().get(0), true);
//        System.out.println("probablityCk = " + probablityCk);
//        Symptom.getAllSymptoms().forEach((Symptom t) -> {
//            try {
////                System.out.println("t = " + t);
//                double entropy = getEntropy(t);
////                System.out.println("entropy = " + entropy);
//            } catch (IOException ex) {
//                Logger.getLogger(Entropy.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }

}
