package by.lozovenko.xmlparser.entity;

public class InternetTraffic {
    private int value;
    private TrafficUnit unit;

    public InternetTraffic() {
    }

    public InternetTraffic(int value, TrafficUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TrafficUnit getUnit() {
        return unit;
    }

    public void setUnit(TrafficUnit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternetTraffic that = (InternetTraffic) o;

        if (value != that.value) return false;
        return unit == that.unit;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\tInternetTraffic");
        sb.append("\n\t\tvalue=").append(value);
        sb.append("\n\t\tunit=").append(unit);
        return sb.toString();
    }
}
