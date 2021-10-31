package by.lozovenko.xmlparser.entity;

import java.math.BigDecimal;

public class SmartphoneTariff extends AbstractTariff{
    private InternetTraffic includedInternetTraffic;
    private int includeMinutes;
    private BigDecimal trafficCostOverIncluded;
    private CallPrice callPrice;

    public SmartphoneTariff(){
    }
    public SmartphoneTariff(String tariffId, String name, MobileOperator operatorName, BigDecimal payroll, TariffParameter parameter,
                            InternetTraffic includedInternetTraffic, int includeMinutes, BigDecimal trafficCostOverIncluded,
                            CallPrice callPrice) {
        super(tariffId, name, operatorName, payroll, parameter);
        this.includedInternetTraffic = includedInternetTraffic;
        this.includeMinutes = includeMinutes;
        this.trafficCostOverIncluded = trafficCostOverIncluded;
        this.callPrice = callPrice;
    }

    public InternetTraffic getIncludedInternetTraffic() {
        return includedInternetTraffic;
    }

    public void setIncludedInternetTraffic(InternetTraffic includedInternetTraffic) {
        this.includedInternetTraffic = includedInternetTraffic;
    }

    public int getIncludeMinutes() {
        return includeMinutes;
    }

    public void setIncludeMinutes(int includeMinutes) {
        this.includeMinutes = includeMinutes;
    }

    public BigDecimal getTrafficCostOverIncluded() {
        return trafficCostOverIncluded;
    }

    public void setTrafficCostOverIncluded(BigDecimal trafficCostOverIncluded) {
        this.trafficCostOverIncluded = trafficCostOverIncluded;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(includedInternetTraffic);
        sb.append("\n\ttrafficCostOverIncluded=").append(trafficCostOverIncluded);
        sb.append("\n\tincludeMinutes=").append(includeMinutes);
        sb.append(callPrice);
        return sb.toString();
    }
}
