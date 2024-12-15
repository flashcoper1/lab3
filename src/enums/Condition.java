package enums;

public enum Condition {

    POOR("бедняжечка"),

    NORMAL("средний класс"),

    RICH("богатейший"),

    SUPERRICH("он миллиардер");

    private final String condition;

    Condition(String condition){
        this.condition = condition;
    }

    @Override
    public String toString(){
        return this.condition;
    }
}