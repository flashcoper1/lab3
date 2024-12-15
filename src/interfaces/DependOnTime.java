package interfaces;

import enums.Time;

public interface DependOnTime {
    boolean canPerformActions(Time timeOfDay);
}
