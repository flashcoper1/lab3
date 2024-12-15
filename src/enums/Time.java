package enums;

public enum Time {
    EARLY_MORNING("раннее утро",0),
    MORNING("утро",1),
    DAY("день",2),
    EVENING("вечер",3),
    LATE_EVENING("поздний вечер",4),
    NIGHT("ночь",5);

    private final String name;
    private final int value;
    Time(String name,int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Time fromValue(int value) {
        for (Time time : values()) {
            if (time.value == value) {
                return time;
            }
        }
        throw new IllegalArgumentException("Нет времени суток с таким значением: " + value);
    }

    @Override
    public String toString(){
        return name;
    }
}
