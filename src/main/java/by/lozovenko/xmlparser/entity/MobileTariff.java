package by.lozovenko.xmlparser.entity;


import java.math.BigDecimal;

public class MobileTariff extends AbstractTariff{
    private int includeMinutes;
    private CallPrice callPrice;

    public MobileTariff(){
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
}
