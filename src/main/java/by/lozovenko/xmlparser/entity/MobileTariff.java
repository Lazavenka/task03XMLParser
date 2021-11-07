package by.lozovenko.xmlparser.entity;


import java.math.BigDecimal;

public class MobileTariff extends AbstractTariff {
    private int includeMinutes;
    private CallPrice callPrice;

    public MobileTariff() {
    }

    public MobileTariff(String tariffId, String name, MobileOperator operatorName, BigDecimal payroll,
                        TariffParameter parameter, int includeMinutes, CallPrice callPrice) {
        super(tariffId, name, operatorName, payroll, parameter);
        this.includeMinutes = includeMinutes;
        this.callPrice = callPrice;
    }

    public int getIncludeMinutes() {
        return includeMinutes;
    }

    public void setIncludeMinutes(int includeMinutes) {
        this.includeMinutes = includeMinutes;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MobileTariff that = (MobileTariff) o;

        if (includeMinutes != that.includeMinutes) return false;
        return callPrice != null ? callPrice.equals(that.callPrice) : that.callPrice == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + includeMinutes;
        result = 31 * result + (callPrice != null ? callPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n\tincludeMinutes=").append(includeMinutes);
        sb.append(callPrice);
        return sb.toString();
    }
}
