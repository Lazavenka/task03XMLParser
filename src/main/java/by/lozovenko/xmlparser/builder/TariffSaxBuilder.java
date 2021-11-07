package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TariffSaxBuilder extends AbstractTariffBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private XMLReader xmlReader;
    private TariffHandler handler;

    public TariffSaxBuilder() {
        handler = new TariffHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.log(Level.ERROR, "SAX parse doesn't work!", e);
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
        setTariffs(handler.getTariffs());
    }
}
