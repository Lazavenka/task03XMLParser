package by.lozovenko.xmlparser.builder;

import java.util.HashMap;
import java.util.Map;

public enum TariffXmlTag {
    TARIFFS("Tariffs"),
    ABSTRACT_TARIFFS("AbstractTariffs"),
    INTERNET_TARIFF("internetTariff"),
    MOBILE_TARIFF("mobileTariff"),
    SMARTPHONE_TARIFF("smartphoneTariff"),
    INCLUDE_TRAFFIC("includeTraffic"),
    TARIFF_PARAMETERS("tariffParameters"),
    CALL_PRICE("callPrice"),
    TARIFF_ID("tariffId"),
    UNIT("unit"),
    NAME("name"),
    PAYROLL("payroll"),
    OPERATOR("operator"),
    TRAFFIC_COST("trafficCostOverIncluded"),
    FAVOURITE_NUMBERS("favouriteNumbers"),
    CONNECTION_PAYMENT("connectionPayment"),
    BILLING("billing"),
    INCLUDED_MINUTES("includedMinutes"),
    INSIDE_NETWORK_CALL_PRICE("insideNetworkCallPrice"),
    OUTSIDE_NETWORK_CALL_PRICE("outsideNetworkCallPrice"),
    STATIONARY_PHONE_CALL_PRICE("stationaryPhoneCallPrice"),
    VALUE("value");
    private static final Map<String, TariffXmlTag> BY_LABEL;

    private final String label;

    static {
        BY_LABEL = new HashMap<>();
        for (TariffXmlTag tag: values()) {
            BY_LABEL.put(tag.label, tag);
        }
    }
    TariffXmlTag(String label) {
        this.label = label;
    }
    public static TariffXmlTag valueOfLabel(String label){
        return BY_LABEL.get(label);
    }
    public String getValue() {
        return label;
    }
}
