package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.*;

import java.math.BigDecimal;

public class TestTariffProvider {
    public static AbstractTariff createExpectedTariff(){
        SmartphoneTariff smartphoneTariff = new SmartphoneTariff();
        smartphoneTariff.setTariffId("ID-9999");
        smartphoneTariff.setName("TestName");
        smartphoneTariff.setPayroll(BigDecimal.valueOf(99.999));
        smartphoneTariff.setOperatorName(MobileOperator.MTS);
        TariffParameter tariffParameter = new TariffParameter(10, BigDecimal.valueOf(10.001), Billing.MINUTE);
        smartphoneTariff.setParameter(tariffParameter);
        smartphoneTariff.setIncludeMinutes(1000);
        InternetTraffic internetTraffic = new InternetTraffic(1000, TrafficUnit.MB);
        smartphoneTariff.setIncludedInternetTraffic(internetTraffic);
        smartphoneTariff.setTrafficCostOverIncluded(BigDecimal.valueOf(0.05));
        CallPrice callPrice = new CallPrice(BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.12));
        smartphoneTariff.setCallPrice(callPrice);
        return smartphoneTariff;
    }
}
