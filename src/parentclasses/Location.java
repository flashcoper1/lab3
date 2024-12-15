package parentclasses;

import java.util.LinkedList;
import java.util.Objects;

public class Location {
    protected String name;

    protected LinkedList<UObject> UObjects;

    public Location(String name){
        this.name = name;
        this.UObjects = new LinkedList<>();
    }

    public String getName(){
        return name;
    }

    public LinkedList<UObject> getObjects(){
        return UObjects;
    }

    public void addObject(UObject UObject){
        this.UObjects.add(UObject);
    }

    public void removeUObject(UObject UObject) {
        this.UObjects.remove(UObject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Objects.equals(name, location.getName())) return false;

        return Objects.equals(UObjects, location.UObjects);
    }

    @Override
    public int hashCode(){
        int result = 4;
        result = 33 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
