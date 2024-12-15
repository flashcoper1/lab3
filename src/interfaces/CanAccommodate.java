package interfaces;

import childclasses.Person;
import parentclasses.UObject;

import java.util.LinkedList;

public interface CanAccommodate {
    LinkedList<UObject> getInside();

    void put(UObject toAdd);

    void take(UObject toRemove);


}
