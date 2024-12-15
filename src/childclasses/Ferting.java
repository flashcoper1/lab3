package childclasses;

import parentclasses.Location;
import parentclasses.UObject;
import enums.Nominal;

public class Ferting extends UObject{
    private Nominal nominal;
    public Ferting(String name, Location location,int amount, Nominal nominal) {
        super(name, location,amount);
        this.nominal = nominal;
    }

    public int getValue() {
        return this.nominal.getValue();
    }

    public int getTotalValue() {
        return nominal.getValue() * amount;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public boolean equals(Ferting f) {
        return this.getValue() == f.getValue();
    }

    @Override
    public String toString() {
        return getAmount() + " шт. " + getName() + " номинала " + nominal.toString();
    }
}
