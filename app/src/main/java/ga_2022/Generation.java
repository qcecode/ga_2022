package ga_2022;

import java.util.ArrayList;

public class Generation {
    private ArrayList<Faltung> faltungList;
    private double avgFitness ;

    public Generation(ArrayList<Faltung> faltungList) {
        this.faltungList = faltungList ;
        avgFitness();
    }

    public ArrayList<Faltung> getFaltungList() {
        return faltungList;
    }

    public void setFaltungList(ArrayList<Faltung> faltungList) {
        this.faltungList = faltungList;
    }
    
    private void avgFitness(){
        avgFitness = 0;
        for(int i = 0; i < faltungList.size(); i++){
            avgFitness += faltungList.get(i).getFitness();
        }
        avgFitness = avgFitness/faltungList.size();
    }

    public double getAvgFitness(){
        return avgFitness;
    }
}
