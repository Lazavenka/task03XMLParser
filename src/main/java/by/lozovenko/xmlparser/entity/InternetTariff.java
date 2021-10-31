package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class InternetTariff extends AbstractTariff{
    private int includedInternetTraffic;
    private BigDecimal trafficCostOverIncluded;
    public InternetTariff(){}
    public InternetTariff(String tariffId, String name, MobileOperator operatorName, BigDecimal payroll, TariffParameter parameter,
                          int includedInternetTraffic, BigDecimal trafficCostOverIncluded) {
        super(tariffId, name, operatorName, payroll, parameter);
        this.includedInternetTraffic = includedInternetTraffic;
        this.trafficCostOverIncluded = trafficCostOverIncluded;
    }

    public int getIncludedInternetTraffic() {
        return includedInternetTraffic;
    }

    public void setIncludedInternetTraffic(int includedInternetTraffic) {
        this.includedInternetTraffic = includedInternetTraffic;
    }

    public BigDecimal getTrafficCostOverIncluded() {
        return trafficCostOverIncluded;
    }

    public void setTrafficCostOverIncluded(BigDecimal trafficCostOverIncluded) {
        this.trafficCostOverIncluded = trafficCostOverIncluded;
    }
}
