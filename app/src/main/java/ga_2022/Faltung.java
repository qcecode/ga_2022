package ga_2022;

import java.util.ArrayList;
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
        fitness();
    }
    public Faltung(Faltung f){
        this.faltung = f.faltung;
        this.hydrophob = f.hydrophob;

        this.knotenList = new ArrayList<>(f.knotenList);
        this.bondSet = new LinkedHashSet<>(f.bondSet);
        this.overlapList = new ArrayList<>(f.overlapList);

        this.fitness = f.fitness;
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

    public void fitness(){
        final int length = this.faltung.length();
        int x = length;
        int y = length;
        double overlap = 0;
        int bonds = 0;
        this.fitness = 0;
        ArrayList<Knoten> matrix[][] = new ArrayList[length*2][length*2];
        char lastDirketion = 'u';
        
        for (int j = 0; j <= length; j++){
            boolean hydrophob = false;
            if(this.hydrophob.charAt(j) == '1'){
                hydrophob = true;
            }
            Knoten k = new Knoten(j, hydrophob);
            if( matrix[x][y] == null){
                matrix[x][y] = new ArrayList<Knoten>();
            }
            matrix[x][y].add(k);

            if(j < length){
                char nextDirection = getDirection(lastDirketion, this.faltung.charAt(j));
                if( nextDirection == 'r'){x = x+1;}
                else if( nextDirection == 'l'){x = x-1;}
                else if( nextDirection == 'u'){y = y+1;}
                else if( nextDirection == 'd'){y = y-1;}
                lastDirketion = nextDirection;
            }
        }

        //count contacts 
        for(int j = 0; j <= length*2-1; j++){
            for(int k = 0; k <= length*2-1; k++){
                if(matrix[j][k] != null){
                    if( matrix[j][k].size() >= 2 ){
                        overlap += matrix[j][k].size() -1;
                        Overlap newOverlap = new Overlap();
                        for(int l = 0; l < matrix[j][k].size(); l++){
                            newOverlap.addKnoten(matrix[j][k].get(l));
                        }
                        this.addOverlapp(newOverlap);
                    }
                    for(int n = 0 ; n < matrix[j][k].size(); n++){
                        if (matrix[j][k].get(n).hydrophob == true){
                            int number = matrix[j][k].get(n).knotenNummer ;
                            if(matrix[j][k+1] != null){
                                for(int i = 0 ; i < matrix[j][k+1].size(); i++){              
                                    if(matrix[j][k+1].get(i).hydrophob == true
                                    && matrix[j][k+1].get(i).knotenNummer != number+1 
                                    && matrix[j][k+1].get(i).knotenNummer != number-1  ){
                                        bonds = bonds + 1;
                                        //System.out.println("bond: " +matrix[j][k+1].get(i).knotenNummer +number);
                                        this.addBond( matrix[j][k].get(n),  matrix[j][k+1].get(i));
                                    }
                                }
                            }
                            if(matrix[j][k-1] != null){
                                for(int i = 0 ; i < matrix[j][k-1].size(); i++){
                                    if(matrix[j][k-1].get(i).hydrophob == true
                                    && matrix[j][k-1].get(i).knotenNummer != number +1 
                                    && matrix[j][k-1].get(i).knotenNummer != number -1  ){
                                        bonds = bonds + 1;
                                        //System.out.println("bond: " +matrix[j][k-1].get(i).knotenNummer +number);
                                    this.addBond( matrix[j][k].get(n),  matrix[j][k-1].get(i));
                                    }
                                }
                            }
                            if(matrix[j+1][k] != null){
                                for(int i = 0 ; i < matrix[j+1][k].size(); i++){
                                    if(matrix[j+1][k].get(i).hydrophob == true
                                    && matrix[j+1][k].get(i).knotenNummer != number +1 
                                    && matrix[j+1][k].get(i).knotenNummer != number -1  ){
                                        bonds = bonds + 1;
                                        //System.out.println("bond: " +matrix[j+1][k].get(i).knotenNummer +number);
                                        this.addBond( matrix[j][k].get(n),  matrix[j+1][k].get(i));
                                    }
                                }
                            }
                            if(matrix[j-1][k] != null){
                                    for(int i = 0 ; i < matrix[j-1][k].size(); i++){
                                    if(matrix[j-1][k].get(i).hydrophob == true
                                    && matrix[j-1][k].get(i).knotenNummer != number +1 
                                    && matrix[j-1][k].get(i).knotenNummer != number -1  ){
                                        bonds = bonds + 1;
                                        //System.out.println("bond: " +matrix[j-1][k].get(i).knotenNummer +number);
                                        this.addBond( matrix[j][k].get(n),  matrix[j-1][k].get(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        bonds = bonds/2; // to stop double numbers
        //System.out.println("overlaps: "+ overlap);
        //System.out.println("bonds: " +bonds);
        this.fitness = ((bonds)+0.1)/((overlap)+1);
        //System.out.println("fitness: " +fitness);
    }

    private char getDirection(char lastDirketion, char heading){
        char nextDirection = lastDirketion;

        if(heading == 'r'){
            if( lastDirketion == 'r'){nextDirection ='d';}
            else if( lastDirketion == 'l'){nextDirection ='u';}
            else if( lastDirketion == 'u'){nextDirection ='r';}
            else if( lastDirketion == 'd'){nextDirection ='l';}
        } 
        else if(heading == 'l'){
            if( lastDirketion == 'r'){nextDirection ='u';}
            else if( lastDirketion == 'l'){nextDirection ='d';}
            else if( lastDirketion == 'u'){nextDirection ='l';}
            else if( lastDirketion == 'd'){nextDirection ='r';}
        }   
        return nextDirection;
    }
}
