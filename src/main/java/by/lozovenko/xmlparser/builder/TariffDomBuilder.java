package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.AbstractTariff;
import by.lozovenko.xmlparser.entity.InternetTariff;
import by.lozovenko.xmlparser.entity.SmartphoneTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


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
    public void buildTariffs(String uri) {

    }
    private AbstractTariff buildTariff(Element tariffElement){
        AbstractTariff tariff = null;
        switch (tariffElement.getTagName()) {
            case "internetTariff" -> tariff = buildInternetTariff(tariffElement);
            case ""
        }
        return tariff;
    }
    private static String getElementTextContent(Element element, String elementName){
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        return node.getTextContent();
    }
    private static AbstractTariff buildInternetTariff(Element tariffElement){
        return new InternetTariff();
    }
}
