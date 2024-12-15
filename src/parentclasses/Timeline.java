package parentclasses;

import enums.Time;

public class Timeline {
    private static Time currentTime;

    public Timeline(Time startTime) {
        currentTime = startTime;
    }

    public static Time getCurrentTime() {
        return currentTime;
    }

    public static void addTime() {
        int nextValue = (currentTime.getValue() + 1) % Time.values().length;
        currentTime = Time.fromValue(nextValue);
    }


}
