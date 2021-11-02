package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class InternetTariff extends AbstractTariff {
    private InternetTraffic includedInternetTraffic;
    private BigDecimal trafficCostOverIncluded;

    public InternetTariff() {
    }

    public InternetTariff(String tariffId, String name, MobileOperator operatorName, BigDecimal payroll, TariffParameter parameter,
                          InternetTraffic includedInternetTraffic, BigDecimal trafficCostOverIncluded) {
        super(tariffId, name, operatorName, payroll, parameter);
        this.includedInternetTraffic = includedInternetTraffic;
        this.trafficCostOverIncluded = trafficCostOverIncluded;
    }

    public InternetTraffic getIncludedInternetTraffic() {
        return includedInternetTraffic;
    }

    public void setIncludedInternetTraffic(InternetTraffic includedInternetTraffic) {
        this.includedInternetTraffic = includedInternetTraffic;
    }

    public BigDecimal getTrafficCostOverIncluded() {
        return trafficCostOverIncluded;
    }

    public void setTrafficCostOverIncluded(BigDecimal trafficCostOverIncluded) {
        this.trafficCostOverIncluded = trafficCostOverIncluded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InternetTariff that = (InternetTariff) o;

        if (includedInternetTraffic != null ? !includedInternetTraffic.equals(that.includedInternetTraffic) : that.includedInternetTraffic != null)
            return false;
        return trafficCostOverIncluded != null ? trafficCostOverIncluded.equals(that.trafficCostOverIncluded) : that.trafficCostOverIncluded == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (includedInternetTraffic != null ? includedInternetTraffic.hashCode() : 0);
        result = 31 * result + (trafficCostOverIncluded != null ? trafficCostOverIncluded.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(includedInternetTraffic);
        sb.append("\n\ttrafficCostOverIncluded=").append(trafficCostOverIncluded);
        return sb.toString();
    }


}
