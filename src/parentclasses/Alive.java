package parentclasses;


import java.util.LinkedList;

public abstract class Alive {
    private final String name;

    protected Location location;

    private final LinkedList<Action> doing;

    public Alive(String name, Location location){
        this.name = name;
        this.doing = new LinkedList<>();
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

    public String getDoingString(){
        StringBuilder text = new StringBuilder("As of now, " + this.getName() + " is: ");
        if (!this.getDoing().isEmpty()){
            text.append(this.getDoing().toString());
        } else {
            text.append("doing nothing");
        }
        return text.toString();
    }

    public LinkedList<Action> getDoing(){
        return doing;
    }

    public void addDoing(Action action){
        this.doing.add(action);
    }

    public void removeDoing(Action action){
        this.doing.remove(action);
    }


}
