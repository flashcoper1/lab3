package childclasses;

import enums.Time;
import interfaces.DependOnTime;
import parentclasses.Location;

public class Building extends Location implements DependOnTime {
    private Time openingTime;
    private Time closingTime;
    public Building(String locname,Time openingTime, Time closingTime) {
        super(locname);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public boolean canPerformActions(Time timeOfDay) {
        return timeOfDay.getValue() >= openingTime.getValue() && timeOfDay.getValue() <= closingTime.getValue();
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}