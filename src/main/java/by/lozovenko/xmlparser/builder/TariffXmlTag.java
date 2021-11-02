package by.lozovenko.xmlparser.builder;

import java.util.Optional;

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

    private final String label;

    TariffXmlTag(String label) {
        this.label = label;
    }
    public static Optional<TariffXmlTag> valueOfLabel(String label){
        for (TariffXmlTag tariffXmlTag : values()){
            if (tariffXmlTag.getValue().equals(label)){
                return Optional.of(tariffXmlTag);
            }
        }
        return Optional.empty();
    }
    public String getValue() {
        return label;
    }
}
