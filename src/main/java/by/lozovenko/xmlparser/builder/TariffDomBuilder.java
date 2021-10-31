package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;


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
            tariffList = root.getElementsByTagName("internetTariff");
            buildElements(tariffList);
            tariffList = root.getElementsByTagName("mobileTariff");
            buildElements(tariffList);
            tariffList = root.getElementsByTagName("smartphoneTariff");
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
                double trafficCostOverIncluded = Double.parseDouble(getElementTextContent(tariffElement, "trafficCostOverIncluded"));
                BigDecimal trafficCostOverIncludedBigDec = BigDecimal.valueOf(trafficCostOverIncluded);
                internetTariff.setTrafficCostOverIncluded(trafficCostOverIncludedBigDec);
            }
            case "mobileTariff" -> {
                tariff = new MobileTariff();
                MobileTariff mobileTariff = (MobileTariff) tariff;
                Element callPriceElement = (Element) tariffElement.getElementsByTagName("callPrice").item(0);
                int includedMinutes = Integer.parseInt(getElementTextContent(tariffElement, "includedMinutes"));
                mobileTariff.setIncludeMinutes(includedMinutes);
                CallPrice callPrice = getCallPriceFromElement(callPriceElement);
                mobileTariff.setCallPrice(callPrice);
            }
            case "smartphoneTariff" -> {
                tariff = new SmartphoneTariff();
                SmartphoneTariff smartphoneTariff = (SmartphoneTariff) tariff;
                InternetTraffic internetTraffic = getInternetTrafficFromElement(tariffElement);
                smartphoneTariff.setIncludedInternetTraffic(internetTraffic);
                double trafficCostOverIncluded = Double.parseDouble(getElementTextContent(tariffElement, "trafficCostOverIncluded"));
                BigDecimal trafficCostOverIncludedBigDec = BigDecimal.valueOf(trafficCostOverIncluded);
                smartphoneTariff.setTrafficCostOverIncluded(trafficCostOverIncludedBigDec);
                Element callPriceElement = (Element) tariffElement.getElementsByTagName("callPrice").item(0);
                int includedMinutes = Integer.parseInt(getElementTextContent(tariffElement, "includedMinutes"));
                smartphoneTariff.setIncludeMinutes(includedMinutes);
                CallPrice callPrice = getCallPriceFromElement(callPriceElement);
                smartphoneTariff.setCallPrice(callPrice);
            }
            default -> throw new IllegalArgumentException(tariffElement.getTagName());
        }
        tariff.setTariffId(tariffElement.getAttribute("tariffId"));
        tariff.setName(getElementTextContent(tariffElement, "name"));
        MobileOperator mobileOperator = MobileOperator.valueOf(getElementTextContent(tariffElement,
                "operator"));
        tariff.setOperatorName(mobileOperator);
        double payroll = Double.parseDouble(getElementTextContent(tariffElement, "payroll"));
        tariff.setPayroll(BigDecimal.valueOf(payroll));

        Element tariffParameterElement = (Element) tariffElement.getElementsByTagName("tariffParameters").item(0);
        int favouriteNumbers = Integer.parseInt(getElementTextContent(tariffParameterElement, "favouriteNumbers"));
        double connectionPayment = Double.parseDouble(getElementTextContent(tariffParameterElement, "connectionPayment"));
        BigDecimal connectionPaymentBigDec = BigDecimal.valueOf(connectionPayment);
        Billing billing = Billing.valueOf(getElementTextContent(tariffParameterElement, "billing"));
        TariffParameter tariffParameter = new TariffParameter(favouriteNumbers, connectionPaymentBigDec, billing);

        tariff.setParameter(tariffParameter);

        return tariff;
    }
    private String getElementTextContent(Element element, String elementName){
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        System.out.println(elementName);
        return node.getTextContent();
    }
    private CallPrice getCallPriceFromElement(Element element){
        double insideNetworkCallPrice = Double.parseDouble(getElementTextContent(element, "insideNetworkCallPrice"));
        BigDecimal insideNetworkBigDec = BigDecimal.valueOf(insideNetworkCallPrice);
        double outsideNetworkCallPrice = Double.parseDouble(getElementTextContent(element, "outsideNetworkCallPrice"));
        BigDecimal outsideNetworkBigDec = BigDecimal.valueOf(outsideNetworkCallPrice);
        double stationaryPhoneCallPrice = Double.parseDouble(getElementTextContent(element, "stationaryPhoneCallPrice"));
        BigDecimal stationaryPhoneBigDec = BigDecimal.valueOf(stationaryPhoneCallPrice);
        return new CallPrice(insideNetworkBigDec, outsideNetworkBigDec, stationaryPhoneBigDec);
    }

    private InternetTraffic getInternetTrafficFromElement(Element element){
        Element includeTrafficElement = (Element) element.getElementsByTagName("includeTraffic").item(0);
        InternetUnit unit = InternetUnit.valueOf(includeTrafficElement.getAttribute("unit").toUpperCase());
        int value = Integer.parseInt(getElementTextContent(includeTrafficElement, "value"));
        return new InternetTraffic(value, unit);
    }

}
