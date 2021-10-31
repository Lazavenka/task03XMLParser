package by.lozovenko.xmlparser.entity;

public class InternetTraffic {
    private int value;
    private InternetUnit unit;

    public InternetTraffic(int value, InternetUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public InternetUnit getUnit() {
        return unit;
    }

    public void setUnit(InternetUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\tInternetTraffic");
        sb.append("\n\t\tvalue=").append(value);
        sb.append("\n\t\tunit=").append(unit);
        return sb.toString();
    }
}
