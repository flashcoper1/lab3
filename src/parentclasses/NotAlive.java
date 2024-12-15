package parentclasses;

import java.util.LinkedList;

public abstract class NotAlive {
    private final String name;

    protected Location location;

    public NotAlive(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location newLocation){
        this.location = newLocation;
    }


}
