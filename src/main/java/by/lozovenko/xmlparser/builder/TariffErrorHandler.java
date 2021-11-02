package by.lozovenko.xmlparser.builder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.WARN, "{} - {}\n" +
                "{}", getInfo(exception), exception.getMessage(), exception.getSystemId());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.ERROR, "{} - {}\n{}", getInfo(exception), exception.getMessage(), exception.getSystemId());

    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.log(Level.FATAL, "{} - {}\n{}", getInfo(exception), exception.getMessage(), exception.getSystemId());

    }

    private String getInfo(SAXParseException exception) {
        return exception.getLineNumber() + " : " + exception.getColumnNumber();
    }
}
