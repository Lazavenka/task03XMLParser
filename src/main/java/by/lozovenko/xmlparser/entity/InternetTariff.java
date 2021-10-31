package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class InternetTariff extends AbstractTariff{
    private InternetTraffic includedInternetTraffic;
    private BigDecimal trafficCostOverIncluded;
    public InternetTariff(){}
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
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(includedInternetTraffic);
        sb.append("\n\ttrafficCostOverIncluded=").append(trafficCostOverIncluded);
        return sb.toString();
    }
}
