package childclasses;

import interfaces.CanAccommodate;
import interfaces.HasInventory;
import interfaces.HasStocks;
import parentclasses.Location;
import parentclasses.UObject;

import java.util.LinkedList;

public class FireproofCabinet extends UObject implements CanAccommodate {
    private final LinkedList<UObject> inventory;

    public FireproofCabinet(String name, Location location,int amount) {
        super(name,location,amount);
        this.inventory = new LinkedList<>();
    }
    @Override
    public void setLocation(Location newLocation){
        this.location = newLocation;
        for(UObject item : this.inventory){
            item.setLocation(newLocation);
        }
    }

    @Override
    public LinkedList<UObject> getInside() {
        return inventory;
    }

    @Override
    public void put(UObject toAdd) {
        this.inventory.add(toAdd);
        toAdd.setLocation(this.location);
    }

    @Override
    public void take(UObject toRemove) {
        this.inventory.remove(toRemove);
    }

    public void printInventory() {
        System.out.println("Содержимое " + getName() + ":");
        for (UObject uObject : getInside()) {
            System.out.println("- " + uObject);
        }
    }
}
