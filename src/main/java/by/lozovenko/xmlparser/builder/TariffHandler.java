package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static by.lozovenko.xmlparser.util.XmlParserUtil.stringToBigDecimal;

public class TariffHandler extends DefaultHandler {

    private Set<AbstractTariff> tariffs;
    private AbstractTariff current;
    private TariffParameter currentTariffParameter;
    private CallPrice currentCallPrice;
    private InternetTraffic currentInternetTraffic;
    private TariffXmlTag currentXmlTag;
    private EnumSet<TariffXmlTag> complexTypes;
    private EnumSet<TariffXmlTag> withText;

    public TariffHandler() {
        tariffs = new HashSet<>();
        complexTypes = EnumSet.range(TariffXmlTag.INTERNET_TARIFF, TariffXmlTag.CALL_PRICE);
        withText = EnumSet.range(TariffXmlTag.NAME, TariffXmlTag.VALUE);
    }

    public Set<AbstractTariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        currentXmlTag = TariffXmlTag.valueOfLabel(qName);
        if (complexTypes.contains(currentXmlTag)) {
            switch (currentXmlTag) {
                case INTERNET_TARIFF -> {
                    current = new InternetTariff();
                    String id = attrs.getValue(TariffXmlTag.TARIFF_ID.getValue());
                    current.setTariffId(id);
                }
                case SMARTPHONE_TARIFF -> {
                    current = new SmartphoneTariff();
                    String id = attrs.getValue(TariffXmlTag.TARIFF_ID.getValue());
                    current.setTariffId(id);
                }
                case MOBILE_TARIFF -> {
                    current = new MobileTariff();
                    String id = attrs.getValue(TariffXmlTag.TARIFF_ID.getValue());
                    current.setTariffId(id);
                }
                case TARIFF_PARAMETERS -> currentTariffParameter = new TariffParameter();
                case CALL_PRICE -> currentCallPrice = new CallPrice();
                case INCLUDE_TRAFFIC -> {
                    currentInternetTraffic = new InternetTraffic();
                    String trafficUnit = attrs.getValue(TariffXmlTag.UNIT.getValue());
                    currentInternetTraffic.setUnit(TrafficUnit.valueOf(trafficUnit));
                }
            }
        } else {
            TariffXmlTag temp = TariffXmlTag.valueOfLabel(qName);
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentXmlTag == null) {
            return;
        }
        String data = new String(ch, start, length);
        switch (currentXmlTag) {
            case NAME -> current.setName(data);
            case PAYROLL -> current.setPayroll(stringToBigDecimal(data));
            case OPERATOR -> current.setOperatorName(MobileOperator.valueOf(data));
            case TRAFFIC_COST -> {
                if (current.getClass() == SmartphoneTariff.class) {
                    ((SmartphoneTariff) current).setTrafficCostOverIncluded(stringToBigDecimal(data));
                } else {
                    ((InternetTariff) current).setTrafficCostOverIncluded(stringToBigDecimal(data));
                }
            }
            case FAVOURITE_NUMBERS -> currentTariffParameter.setFavouriteNumbers(Integer.parseInt(data));
            case CONNECTION_PAYMENT -> currentTariffParameter.setConnectionPayment(stringToBigDecimal(data));
            case BILLING -> currentTariffParameter.setBilling(Billing.valueOf(data));
            case INCLUDED_MINUTES -> {
                if (current.getClass() == SmartphoneTariff.class) {
                    ((SmartphoneTariff) current).setIncludeMinutes(Integer.parseInt(data));
                } else {
                    ((MobileTariff) current).setIncludeMinutes(Integer.parseInt(data));
                }
            }
            case UNIT -> currentInternetTraffic.setUnit(TrafficUnit.valueOf(data));
            case VALUE -> currentInternetTraffic.setValue(Integer.parseInt(data));
            case INSIDE_NETWORK_CALL_PRICE -> currentCallPrice.setInsideNetworkCallPrice(stringToBigDecimal(data));
            case OUTSIDE_NETWORK_CALL_PRICE -> currentCallPrice.setOutsideNetworkCallPrice(stringToBigDecimal(data));
            case STATIONARY_PHONE_CALL_PRICE -> currentCallPrice.setStationaryPhoneCallPrice(stringToBigDecimal(data));
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        TariffXmlTag tag = TariffXmlTag.valueOfLabel(qName);
        switch (tag) {
            case MOBILE_TARIFF, INTERNET_TARIFF, SMARTPHONE_TARIFF -> {
                tariffs.add(current);
                current = null;
            }
            case TARIFF_PARAMETERS -> {
                current.setParameter(currentTariffParameter);
                currentTariffParameter = null;
            }
            case CALL_PRICE -> {
                if (current.getClass() == SmartphoneTariff.class) {
                    ((SmartphoneTariff) current).setCallPrice(currentCallPrice);
                } else {
                    ((MobileTariff) current).setCallPrice(currentCallPrice);
                }
                currentCallPrice = null;
            }
            case INCLUDE_TRAFFIC -> {
                if (current.getClass() == SmartphoneTariff.class) {
                    ((SmartphoneTariff) current).setIncludedInternetTraffic(currentInternetTraffic);
                } else {
                    ((InternetTariff) current).setIncludedInternetTraffic(currentInternetTraffic);
                }
                currentInternetTraffic = null;
            }
        }
        currentXmlTag = null;
    }
}
