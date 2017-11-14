/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import Calculation.Disease;
import Calculation.Entropy;
import static Calculation.Entropy.getEntropy;
import Calculation.Symptom;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author forsakenMystery
 */
public class BinaryTree {
    
    private static Node root = null;

    /**
     * @return the root
     */
    public static Node getRoot() {
        return root;
    }

    /**
     * @param aRoot the root to set
     */
    public static void setRoot(Node aRoot) {
        root = aRoot;
    }
    
    public BinaryTree() throws IOException {
        root=new Node((Symptom)null);
        buildTree(root, Disease.getDisease(), Symptom.getAllSymptoms());
    }

    public BinaryTree(String addressDisease, String addressSymptom) throws IOException {
        root=new Node((Symptom)null);
        buildTree(root, Disease.getDisease(addressDisease), Symptom.getAllSymptoms(addressSymptom));
    }
    
    private void buildTree(Node start, List<Disease> disease, List<Symptom> symptom) {
        System.out.println("");
        System.out.println("*******************************************************");
        System.out.println("*******************************************************");
        System.out.println("symptom = " + symptom);
        System.out.println("disease = " + disease);
        System.out.println("=======================================================");
        if(disease.size()==1){
            start.setDisease(disease.get(0));
            System.out.println("start = " + start);
            return;
        }
        if(disease.isEmpty()||disease==null){
            return;
        }
        ArrayList<Pair<Symptom, Double>> al = new ArrayList<>();
        symptom.forEach((Symptom t) -> {
            try {
                double entropy = getEntropy(disease, t);
                al.add(new Pair<>(t, entropy));
            } catch (IOException ex) {
                Logger.getLogger(Entropy.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println("al = " + al);
        Pair<Symptom, Double> min = Collections.min(al, (Pair<Symptom, Double> o1, Pair<Symptom, Double> o2) -> Double.compare(o1.getValue(), o2.getValue()));
        System.out.println("min = " + min);
        start.setSymptom(min.getKey());
        start.setCanHave(disease);
        System.out.println("start = " + start);
        List<Symptom> sy = new ArrayList<>();
        for (Symptom s : symptom) {
            if (!s.equals(start.getSymptom())) {
                sy.add(s);
            }
        }
        System.out.println("sy = " + sy);
        List diseaseYes = new ArrayList<>();
        List diseaseNo = new ArrayList<>();        
        for(Disease d:disease){
            if (d.getSymptoms().contains(start.getSymptom())) {
                diseaseYes.add(d);
            } else {
                diseaseNo.add(d);
            }
        }
        System.out.println("diseaseYes = " + diseaseYes);
        System.out.println("diseaseNo = " + diseaseNo);
        start.setYes(new Node((Symptom)null));
        start.setNo(new Node((Symptom)null));
        buildTree(start.getYes(), diseaseYes, sy);
        buildTree(start.getNo(), diseaseNo, sy);
    }

    //        int i = 1;
//        boolean flag = true;
//        while (!q.isEmpty()) {
//            Node poll = null;
//            if (flag) {
//                poll = q.peek();
//            } else {
//                poll = q.poll();
//            }
//            System.out.println("poll = " + poll);
//            if (i < al.size()) {
//                if (flag) {
//                    poll.setYes(new Node(al.get(i).getKey()));
//                    System.out.println("poll.getYes() = " + poll.getYes());
//                    q.add(poll.getYes());
//                } else {
//                    poll.setNo(new Node(al.get(i).getKey()));
//                    System.out.println("poll.getNo() = " + poll.getNo());
//                    q.add(poll.getNo());
//                }
//            }
//            flag = !flag;
//            i++;
//        }
//        while (i < al.size()) {
//            int j=q.size();
//            while (j--!=0) {
//                Node poll = q.poll();
//                poll.setYes(new Node(al.get(i).getKey()));
//                q.add(poll.getYes());
//                poll.setNo(new Node(al.get(i).getKey()));
//                q.add(poll.getNo());
//            }
//            i++;
//        }
    protected static void ldr(Node start) {//yes is left
        if (start.getYes() != null) {
            ldr(start.getYes());
        }
        System.out.println("start = " + start);
        if (start.getNo() != null) {
            ldr(start.getNo());
        }
    }
    
    private static void dlr(Node start) {//yes is left
        System.out.println("start = " + start);
        if (start.getYes() != null) {
            ldr(start.getYes());
        }
        if (start.getNo() != null) {
            ldr(start.getNo());
        }
    }
    
    public int getheight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getheight(root.getYes()), getheight(root.getNo())) + 1;
    }
    
    public static void main(String[] args) throws IOException {
        new Draw(new BinaryTree());
//        new BinaryTree();
    }
    
}
