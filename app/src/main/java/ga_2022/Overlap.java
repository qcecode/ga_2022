package ga_2022;

import java.util.LinkedHashSet;

import com.google.common.base.Objects;

public class Overlap {
    private LinkedHashSet<Knoten> knotenSet;

    public Overlap() {
        knotenSet = new LinkedHashSet<>();
    }
    
    public void addKnoten(Knoten k0) {
        knotenSet.add(k0);
    }

    public LinkedHashSet<Knoten> getKotenSet(){
        return knotenSet;
    }

    public int size(){
        return knotenSet.size();
    }

}
