package ga_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Faltung {
    public String faltung;
    public String hydrophob;

    private ArrayList<Knoten> knotenList;
    private LinkedHashSet<Bond> bondSet;
    private ArrayList<Overlap> overlapList;
    private double fitness;

    public Faltung(String pFaltung, String pHydrophob) {
        this.faltung = pFaltung;
        this.hydrophob = pHydrophob;
        knotenList = new ArrayList<>();
        bondSet = new LinkedHashSet<>();
        overlapList = new ArrayList<>();

        for(int i = 0; i < hydrophob.length(); i++){
            boolean hydrophobBoolean = false;
            if(hydrophob.charAt(i) == '1'){
                hydrophobBoolean = true;
            }
            Knoten k1 = new Knoten(i, hydrophobBoolean);
            knotenList.add(k1);
        }
    }
    
    public int size() {
        return knotenList.size();
    }

    public void addBond(Knoten k1, Knoten k2){
        Bond newBond = new Bond(k1, k2);
        bondSet.add(newBond);
    }
    public LinkedHashSet<Bond> getBondSet(){
        return bondSet;
    }

    public void addOverlapp(Overlap o0){
        overlapList.add(o0);
    }
    
    public ArrayList<Knoten> getKotenList(){
        return knotenList;
    }

    public ArrayList<Overlap> getOverlapList(){
        return overlapList;
    }
  
    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
