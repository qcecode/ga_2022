package ga_2022;

import java.util.Objects;
public class Bond {
    private Knoten k0 , k1 ;

    public Bond(Knoten k0, Knoten k1) {
        if (k0.knotenNummer < k1.knotenNummer){
            this.k0 = k0;
            this.k1 = k1;
        } else {
            this.k0 = k1;
            this.k1 = k0;
        }
    }

    public Knoten getK0() {
        return k0;
    }

    public Knoten getK1() {
        return k1;
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {
    
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
    
        /* Check if o is an instance of Complex or not
            "null instanceof [type]" also returns false */
        if (!(o instanceof Bond)) {
            return false;
        }
            
        // typecast o to Complex so that we can compare data members
        Bond c = (Bond) o;
            
        // Compare the data members and return accordingly
        return k0.knotenNummer == c.k0.knotenNummer && k1.knotenNummer == c.k1.knotenNummer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.toString(k0.knotenNummer)+Integer.toString(k1.knotenNummer));
    }

}
