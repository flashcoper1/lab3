package enums;


public enum WealthLevel {
    POOR(0, 10, "Бедный"),
    MIDDLE(10, 100, "Средне богатый"),
    RICH(100, 1000, "Богач"),
    BILLIONAIRE(1000, Integer.MAX_VALUE, "Миллиардер");

    private final int min; // Минимальное значение диапазона
    private final int max; // Максимальное значение диапазона
    private final String description; // Описание уровня богатства

    WealthLevel(int min, int max, String description) {
        this.min = min;
        this.max = max;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WealthLevel fromValue(int wealth) {
        for (WealthLevel level : WealthLevel.values()) {
            if (wealth >= level.min && wealth < level.max) {
                return level;
            }
        }
        throw new IllegalArgumentException("Некорректное значение богатства: " + wealth);
    }
}
