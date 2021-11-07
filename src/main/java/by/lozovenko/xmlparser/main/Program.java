package by.lozovenko.xmlparser.main;

import by.lozovenko.xmlparser.builder.AbstractTariffBuilder;
import by.lozovenko.xmlparser.exception.ProjectException;
import by.lozovenko.xmlparser.factory.BuilderFactory;
import by.lozovenko.xmlparser.factory.ParserType;
import by.lozovenko.xmlparser.validator.CustomXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class Program {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String XML_FILEPATH = "data/tariffs.xml";

    public static void main(String[] args) throws ProjectException {
        ClassLoader classLoader = CustomXmlValidator.class.getClassLoader();
        URL xmlUrl = classLoader.getResource(XML_FILEPATH);
        String xmlFilepath = xmlUrl.getPath();
        ParserType parser = ParserType.STAX;
        AbstractTariffBuilder tariffBuilder = BuilderFactory.createTariffBuilder(parser);
        CustomXmlValidator.validateXml(xmlFilepath);
        tariffBuilder.buildTariffs(xmlFilepath);
        LOGGER.info(tariffBuilder.getTariffs());
    }
}
