package ga_2022;

import java.util.Objects;

public class Knoten {
    public int knotenNummer;
    public boolean hydrophob;
    

    public Knoten(int knotenNummer, boolean hydrophob) {
        this.knotenNummer = knotenNummer;
        this.hydrophob = hydrophob;
    }

    @Override
    public boolean equals(Object o) {
    
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
    
        /* Check if o is an instance of Complex or not
            "null instanceof [type]" also returns false */
        if (!(o instanceof Knoten)) {
            return false;
        }
            
        // typecast o to Complex so that we can compare data members
        Knoten c = (Knoten) o;
            
        // Compare the data members and return accordingly
        return knotenNummer == c.knotenNummer && hydrophob == c.hydrophob;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knotenNummer);
    }

}
