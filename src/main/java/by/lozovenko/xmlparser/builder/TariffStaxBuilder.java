package by.lozovenko.xmlparser.builder;


import by.lozovenko.xmlparser.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import static by.lozovenko.xmlparser.util.XmlParserUtil.stringToBigDecimal;


public class TariffStaxBuilder extends AbstractTariffBuilder{
    private static final Logger LOGGER = LogManager.getLogger();

    private XMLInputFactory inputFactory;
    public TariffStaxBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }
    @Override
    public void buildTariffs(String filename) {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))){
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOfLabel(name);
                    switch (tag){
                        case SMARTPHONE_TARIFF, MOBILE_TARIFF, INTERNET_TARIFF -> {
                            AbstractTariff tariff = buildTariff(reader, tag);
                            getTariffs().add(tariff);
                        }
                    }
                }
            }
        }catch (XMLStreamException| FileNotFoundException e){
            LOGGER.log(Level.ERROR, "Stax exception!", e);
        }catch (IOException e){
            LOGGER.log(Level.ERROR, "IO exception!", e);
        }

    }
    private AbstractTariff buildTariff(XMLStreamReader reader, TariffXmlTag tariffType) throws XMLStreamException{
        AbstractTariff tariff;
        switch (tariffType){
            case MOBILE_TARIFF -> tariff = new MobileTariff();
            case INTERNET_TARIFF -> tariff = new InternetTariff();
            case SMARTPHONE_TARIFF -> tariff = new SmartphoneTariff();
            default -> throw new EnumConstantNotPresentException(TariffXmlTag.class, tariffType.getValue());
        }
        tariff.setTariffId(reader.getAttributeValue(null, TariffXmlTag.TARIFF_ID.getValue()));
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOfLabel(name);
                    switch (tag) {
                        case TARIFF_PARAMETERS -> {
                            TariffParameter tariffParameter = getXmlTariffParameter(reader);
                            tariff.setParameter(tariffParameter);
                        }
                        case PAYROLL -> {
                            String payroll = getXmlText(reader);
                            tariff.setPayroll(stringToBigDecimal(payroll));
                        }
                        case NAME -> tariff.setName(getXmlText(reader));
                        case OPERATOR -> tariff.setOperatorName(MobileOperator.valueOf(getXmlText(reader)));
                        case INCLUDED_MINUTES -> {
                            if (tariff.getClass() == SmartphoneTariff.class) {
                                ((SmartphoneTariff) tariff).setIncludeMinutes(Integer.parseInt(getXmlText(reader)));
                            } else {
                                ((MobileTariff) tariff).setIncludeMinutes(Integer.parseInt(getXmlText(reader)));
                            }
                        }
                        case INCLUDE_TRAFFIC ->{
                            if (tariff.getClass() == SmartphoneTariff.class) {
                                ((SmartphoneTariff) tariff).setIncludedInternetTraffic(getXmlInternetTraffic(reader));
                            } else {
                                ((InternetTariff) tariff).setIncludedInternetTraffic(getXmlInternetTraffic(reader));
                            }
                        }
                        case CALL_PRICE -> {
                            if (tariff.getClass() == SmartphoneTariff.class) {
                                ((SmartphoneTariff) tariff).setCallPrice(getXmlCallPrice(reader));
                            } else {
                                ((MobileTariff) tariff).setCallPrice(getXmlCallPrice(reader));
                            }
                        }
                        case TRAFFIC_COST -> {
                            BigDecimal trafficCost = stringToBigDecimal(getXmlText(reader));
                            if (tariff.getClass() == SmartphoneTariff.class) {
                                ((SmartphoneTariff) tariff).setTrafficCostOverIncluded(trafficCost);
                            } else {
                                ((InternetTariff) tariff).setTrafficCostOverIncluded(trafficCost);
                            }
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOfLabel(name);
                    switch (tag) {
                        case SMARTPHONE_TARIFF, MOBILE_TARIFF, INTERNET_TARIFF -> {
                            return tariff;
                        }
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tags <smartphoneTariff>|<mobileTariff>|<internetTariff>");
    }
    private TariffParameter getXmlTariffParameter(XMLStreamReader reader) throws XMLStreamException{
        TariffParameter tariffParameter = new TariffParameter();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOfLabel(name);
                    switch (tag){
                        case BILLING -> {
                            Billing billing = Billing.valueOf(getXmlText(reader));
                            tariffParameter.setBilling(billing);
                        }
                        case CONNECTION_PAYMENT -> {
                            BigDecimal connectionPayment = stringToBigDecimal(getXmlText(reader));
                            tariffParameter.setConnectionPayment(connectionPayment);
                        }
                        case FAVOURITE_NUMBERS -> tariffParameter.setFavouriteNumbers(Integer.parseInt(getXmlText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (TariffXmlTag.TARIFF_PARAMETERS.getValue().equals(name)) {
                        return tariffParameter;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tariffParameters>");
    }
    private CallPrice getXmlCallPrice(XMLStreamReader reader)throws XMLStreamException{
        CallPrice callPrice = new CallPrice();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    TariffXmlTag tag = TariffXmlTag.valueOfLabel(name);
                    switch (tag){
                        case INSIDE_NETWORK_CALL_PRICE -> {
                            String insideNetworkCallPrice = getXmlText(reader);
                            callPrice.setInsideNetworkCallPrice(stringToBigDecimal(insideNetworkCallPrice));
                        }
                        case STATIONARY_PHONE_CALL_PRICE -> {
                            String stationaryPhoneCallPrice = getXmlText(reader);
                            callPrice.setStationaryPhoneCallPrice(stringToBigDecimal(stationaryPhoneCallPrice));
                        }
                        case OUTSIDE_NETWORK_CALL_PRICE -> {
                            String outsideNetworkCallPrice = getXmlText(reader);
                            callPrice.setOutsideNetworkCallPrice(stringToBigDecimal(outsideNetworkCallPrice));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (TariffXmlTag.CALL_PRICE.getValue().equals(name)) {
                        return callPrice;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <callPrice>");
    }
    private InternetTraffic getXmlInternetTraffic(XMLStreamReader reader) throws XMLStreamException {
        InternetTraffic internetTraffic = new InternetTraffic();
        TrafficUnit unit = TrafficUnit.valueOf(reader.getAttributeValue(null, TariffXmlTag.UNIT.getValue()));
        internetTraffic.setUnit(unit);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(TariffXmlTag.VALUE.getValue())) {
                        int trafficValue = Integer.parseInt(getXmlText(reader));
                        internetTraffic.setValue(trafficValue);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if(TariffXmlTag.INCLUDE_TRAFFIC.getValue().equals(name)) {
                        return internetTraffic;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <includeTraffic>");
    }
    private String getXmlText(XMLStreamReader reader)throws XMLStreamException{
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
