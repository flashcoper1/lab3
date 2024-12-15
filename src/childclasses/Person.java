package childclasses;

import interfaces.CanAccommodate;
import interfaces.HasInventory;
import parentclasses.Alive;
import parentclasses.Location;
import parentclasses.UObject;
import enums.WealthLevel;
import  childclasses.Ferting;

import java.util.LinkedList;

public class Person extends Alive implements HasInventory {
    private final LinkedList<UObject> inventory;
    private WealthLevel wealthLevel;

    public Person(String name, Location location) {
        super(name, location);
        this.inventory = new LinkedList<>();
        updateWealthLevel();
    }

    @Override
    public void setLocation(Location newLocation){
        this.location = newLocation;
        for(UObject item : this.inventory){
            item.setLocation(newLocation);
        }
    }

    @Override
    public LinkedList<UObject> getInventory() {
        return inventory;
    }

    @Override
    public void addInventory(UObject toAdd) {
        this.inventory.add(toAdd);
        toAdd.setLocation(this.location);
        updateWealthLevel();
    }

    @Override
    public void removeInventory(UObject toRemove) {
        this.inventory.remove(toRemove);
    }

    private void updateWealthLevel() {
        int totalMoney = 0;

        for (UObject uObject : inventory) {
            if (uObject instanceof Ferting ferting) {
                totalMoney += ferting.getTotalValue();
            }
        }

        this.wealthLevel = WealthLevel.fromValue(totalMoney);
    }


    public WealthLevel getWealthLevel() {
        return wealthLevel;
    }

    public void printInventory() {
        System.out.println("Инвентарь " + getName() + ":");
        for (UObject uObject : getInventory()) {
            System.out.println("- " + uObject);
        }
    }
    @Override
    public  void giveItem(Person to, UObject item) {
        if (this.inventory.contains(item)) {
            this.removeInventory(item);
            to.addInventory(item);
            System.out.println(this.getName() + " отдал " + item + " " +  to.getName());
        } else {
            System.out.println("Ошибка: " + this.getName() + " не имеет объекта " + item + " для передачи.");
        }
    }
    @Override
    public <T extends UObject & CanAccommodate> void giveItem(T to, UObject item) {
        if (this.inventory.contains(item)) {
            this.removeInventory(item);
            to.put(item);
            System.out.println(this.getName() + " положил " + item + " в " +  to.getName());
        } else {
            System.out.println("Ошибка: " + this.getName() + " не имеет объекта " + item + " для передачи.");
        }
    }
}
