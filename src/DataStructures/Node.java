/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Calculation.Disease;
import Calculation.Symptom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author forsakenMystery
 */
public class Node {

    private Node yes;
    private Node no;
    private Symptom symptom;
    private Disease disease;
    private List<Disease> canHave;

    public List<Disease> getCanHave() {
        return canHave;
    }

    public String getCanHaveName() {
        String s = "";
        for (int i = 0; i < canHave.size() - 1; i++) {
            s += canHave.get(i).getName() + ",";
        }
        return s + canHave.get(canHave.size() - 1).getName();
    }

    public Disease getDisease() {
        return disease;
    }

    public void setCanHave(List<Disease> canHave) {
        this.canHave = canHave;
    }

    public Node(Symptom symptom) {
        this.symptom = symptom;
        this.yes = null;
        this.no = null;
        disease = null;
        canHave = new ArrayList<>();
    }

    public Node(Disease disease) {
        this.disease = disease;
        this.yes = null;
        this.no = null;
        canHave = new ArrayList<>();
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "[symptom =" + getSymptom() + " , yes = " + getYes() + " , no = " + getNo() + " , disease = " + disease + "]"; //To chymange body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the yes
     */
    public Node getYes() {
        return yes;
    }

    /**
     * @param yes the yes to set
     */
    public void setYes(Node yes) {
        this.yes = yes;
    }

    /**
     * @return the no
     */
    public Node getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(Node no) {
        this.no = no;
    }

    /**
     * @return the symptom
     */
    public Symptom getSymptom() {
        return symptom;
    }

    /**
     * @param symptom the symptom to set
     */
    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public String String() {
        if (disease == null) {
            return getSymptom() + "";
        } else {
            return disease.getName();
        }
    }

}
