package enums;

public enum Nominal {
    ONE(1, "рубль"),
    FIVE(5, "пяточок"),
    FIFTY(50, "пятьдесят"),
    ONE_HUNDRED(100, "сотка"),
    FIVE_HUNDRED(500, "пятьсот"),
    ONE_THOUSAND(1000, "косарик"),
    FIVE_THOUSAND(5000, "пятихат");

    private final int value;
    private final String description;

    Nominal(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  description ;
    }


}


