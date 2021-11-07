package by.lozovenko.xmlparser.validator;

import by.lozovenko.xmlparser.builder.TariffErrorHandler;
import by.lozovenko.xmlparser.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class CustomXmlValidator {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String XML_SCHEMA_FILEPATH = "data/tariffs.xsd";
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private CustomXmlValidator() {
    }

    public static void validateXml(String xmlFilepath) throws ProjectException {
        ClassLoader classLoader = CustomXmlValidator.class.getClassLoader();
        URL schemaURL = classLoader.getResource(XML_SCHEMA_FILEPATH);
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        try {
            Schema schema = factory.newSchema(schemaURL);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilepath);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            LOGGER.log(Level.INFO, "XML file {} is valid!", xmlFilepath);
        } catch (SAXException | IOException e) {
            throw new ProjectException(xmlFilepath + " is not valid!", e);
        }
    }
}
