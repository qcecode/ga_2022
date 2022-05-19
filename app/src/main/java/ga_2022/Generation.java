package ga_2022;

import java.util.ArrayList;

public class Generation {
    private ArrayList<Faltung> faltungList;
    private double avgFitness ;

    private Faltung maxFitness;

    public Generation(ArrayList<Faltung> faltungList) {
        this.faltungList = faltungList ;
        avgFitness();
        maxFitness();
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

    private void maxFitness(){
        maxFitness = faltungList.get(0);
        for(int i = 1; i < faltungList.size(); i++){
            if(maxFitness.getFitness() < faltungList.get(i).getFitness()){
                maxFitness = faltungList.get(i);
            }
        }
    }

    public Faltung getMaxFitness() {
        return maxFitness;
    }

}
