package interfaces;

import childclasses.Person;
import parentclasses.UObject;

import java.util.LinkedList;

public interface HasInventory {

    LinkedList<UObject> getInventory();

    void addInventory(UObject toAdd);

    void removeInventory(UObject toRemove);

    public  void giveItem(Person to, UObject item);

    public <T extends UObject & CanAccommodate> void giveItem(T to, UObject item);
}
/* интерфейс к кодному типу объектов
enumы на каждый объектов с изменением типа


*/