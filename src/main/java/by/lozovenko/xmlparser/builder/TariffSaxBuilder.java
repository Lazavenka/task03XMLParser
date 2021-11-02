package by.lozovenko.xmlparser.builder;


import by.lozovenko.xmlparser.entity.AbstractTariff;
import by.lozovenko.xmlparser.exception.ProjectException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class TariffSaxBuilder extends AbstractTariffBuilder{
    private XMLReader xmlReader;
    private TariffHandler handler;
    public TariffSaxBuilder() throws ProjectException {
        handler = new TariffHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            throw new ProjectException("SAX parse doesn't work!", e);
        }
        xmlReader.setContentHandler(handler);
        xmlReader.setErrorHandler(new TariffErrorHandler());
    }

    @Override
    public void buildTariffs(String uri) throws ProjectException {
        try {
            xmlReader.parse(uri);
        } catch (IOException | SAXException e) {
            throw new ProjectException("File parsing error!" + uri, e);
        }
        tariffs = handler.getTariffs();
    }
}
