package parentclasses;

public class UObject {

    protected final String name;

    protected int amount = 1;

    protected Location location;

    public UObject(String name, Location location,int amount){
        this.name = name;
        this.amount = amount;
        this.location = location;
        this.location.addObject(this);
    }

    public String getName(){
        return this.name;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location newLocation){
        this.location.removeUObject(this);
        this.location = newLocation;
        newLocation.addObject(this);
    }

    public int getAmount(){
        return this.amount;
    }

    public void setAmount(int newAmount){
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return getAmount() + " шт. " + getName();
    }

}