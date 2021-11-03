package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.text.html.HTML;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;

import static by.lozovenko.xmlparser.util.XmlParserUtil.stringToBigDecimal;


public class TariffDomBuilder extends AbstractTariffBuilder{
    private static final Logger LOGGER = LogManager.getLogger();

    private DocumentBuilder documentBuilder;
    public TariffDomBuilder(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            LOGGER.debug("Parser config exception!", e);
        }
    }

    @Override
    public void buildTariffs(String filename) {
        Document document;
        NodeList tariffList;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            tariffList = root.getElementsByTagName(TariffXmlTag.INTERNET_TARIFF.getValue());
            buildElements(tariffList);
            tariffList = root.getElementsByTagName(TariffXmlTag.MOBILE_TARIFF.getValue());
            buildElements(tariffList);
            tariffList = root.getElementsByTagName(TariffXmlTag.SMARTPHONE_TARIFF.getValue());
            buildElements(tariffList);
        }catch (IOException | SAXException e){
            LOGGER.debug("Parsing exception!", e);
        }
    }
    private void buildElements(NodeList nodeList){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element tariffElement = (Element) nodeList.item(i);
            AbstractTariff abstractTariff = buildTariff(tariffElement);
            super.getTariffs().add(abstractTariff);
        }
    }
    private AbstractTariff buildTariff(Element tariffElement){
        AbstractTariff tariff;
        switch (tariffElement.getTagName()) {
            case "internetTariff" -> {
                tariff = new InternetTariff();
                InternetTariff internetTariff = (InternetTariff) tariff;
                InternetTraffic internetTraffic = getInternetTrafficFromElement(tariffElement);
                internetTariff.setIncludedInternetTraffic(internetTraffic);
                String trafficCostOverIncluded = getElementTextContent(tariffElement,
                        TariffXmlTag.TRAFFIC_COST.getValue());
                internetTariff.setTrafficCostOverIncluded(stringToBigDecimal(trafficCostOverIncluded));
            }
            case "mobileTariff" -> {
                tariff = new MobileTariff();
                MobileTariff mobileTariff = (MobileTariff) tariff;
                Element callPriceElement = (Element) tariffElement
                        .getElementsByTagName(TariffXmlTag.CALL_PRICE.getValue()).item(0);
                int includedMinutes = Integer.parseInt(getElementTextContent(tariffElement,
                        TariffXmlTag.INCLUDED_MINUTES.getValue()));
                mobileTariff.setIncludeMinutes(includedMinutes);
                CallPrice callPrice = getCallPriceFromElement(callPriceElement);
                mobileTariff.setCallPrice(callPrice);
            }
            case "smartphoneTariff" -> {
                tariff = new SmartphoneTariff();
                SmartphoneTariff smartphoneTariff = (SmartphoneTariff) tariff;
                InternetTraffic internetTraffic = getInternetTrafficFromElement(tariffElement);
                smartphoneTariff.setIncludedInternetTraffic(internetTraffic);
                String trafficCostOverIncluded = getElementTextContent(tariffElement,
                        TariffXmlTag.TRAFFIC_COST.getValue());
                smartphoneTariff.setTrafficCostOverIncluded(stringToBigDecimal(trafficCostOverIncluded));
                Element callPriceElement = (Element) tariffElement
                        .getElementsByTagName(TariffXmlTag.CALL_PRICE.getValue()).item(0);
                int includedMinutes = Integer.parseInt(getElementTextContent(tariffElement,
                        TariffXmlTag.INCLUDED_MINUTES.getValue()));
                smartphoneTariff.setIncludeMinutes(includedMinutes);
                CallPrice callPrice = getCallPriceFromElement(callPriceElement);
                smartphoneTariff.setCallPrice(callPrice);
            }
            default -> throw new IllegalArgumentException(tariffElement.getTagName());
        }
        tariff.setTariffId(tariffElement.getAttribute(TariffXmlTag.TARIFF_ID.getValue()));
        tariff.setName(getElementTextContent(tariffElement, TariffXmlTag.NAME.getValue()));
        MobileOperator mobileOperator = MobileOperator.valueOf(getElementTextContent(tariffElement,
                TariffXmlTag.OPERATOR.getValue()));
        tariff.setOperatorName(mobileOperator);
        String payroll = getElementTextContent(tariffElement, TariffXmlTag.PAYROLL.getValue());
        tariff.setPayroll(stringToBigDecimal(payroll));

        Element tariffParameterElement = (Element) tariffElement
                .getElementsByTagName(TariffXmlTag.TARIFF_PARAMETERS.getValue()).item(0);
        int favouriteNumbers = Integer.parseInt(getElementTextContent(tariffParameterElement,
                TariffXmlTag.FAVOURITE_NUMBERS.getValue()));
        String connectionPayment = getElementTextContent(tariffParameterElement,
                TariffXmlTag.CONNECTION_PAYMENT.getValue());
        Billing billing = Billing.valueOf(getElementTextContent(tariffParameterElement,
                TariffXmlTag.BILLING.getValue()));
        TariffParameter tariffParameter = new TariffParameter(favouriteNumbers,
                stringToBigDecimal(connectionPayment), billing);
        tariff.setParameter(tariffParameter);

        return tariff;
    }
    private String getElementTextContent(Element element, String elementName){
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        return node.getTextContent();
    }
    private CallPrice getCallPriceFromElement(Element element){
        String insideNetworkCallPrice = getElementTextContent(element, TariffXmlTag.INSIDE_NETWORK_CALL_PRICE.getValue());
        BigDecimal insideNetworkBigDec = stringToBigDecimal(insideNetworkCallPrice);
        String outsideNetworkCallPrice = getElementTextContent(element, TariffXmlTag.OUTSIDE_NETWORK_CALL_PRICE.getValue());
        BigDecimal outsideNetworkBigDec = stringToBigDecimal(outsideNetworkCallPrice);
        String stationaryPhoneCallPrice = getElementTextContent(element, TariffXmlTag.STATIONARY_PHONE_CALL_PRICE.getValue());
        BigDecimal stationaryPhoneBigDec = stringToBigDecimal(stationaryPhoneCallPrice);
        return new CallPrice(insideNetworkBigDec, outsideNetworkBigDec, stationaryPhoneBigDec);
    }

    private InternetTraffic getInternetTrafficFromElement(Element element){
        Element includeTrafficElement = (Element) element
                .getElementsByTagName(TariffXmlTag.INCLUDE_TRAFFIC.getValue()).item(0);
        String unitString = includeTrafficElement.getAttribute(TariffXmlTag.UNIT.getValue());
        TrafficUnit unit = TrafficUnit.valueOf(unitString.toUpperCase());
        int value = Integer.parseInt(getElementTextContent(includeTrafficElement, TariffXmlTag.VALUE.getValue()));
        return new InternetTraffic(value, unit);
    }

}
