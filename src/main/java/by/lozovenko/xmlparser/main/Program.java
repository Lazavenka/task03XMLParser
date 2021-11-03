package by.lozovenko.xmlparser.main;

import by.lozovenko.xmlparser.builder.AbstractTariffBuilder;
import by.lozovenko.xmlparser.exception.ProjectException;
import by.lozovenko.xmlparser.factory.BuilderFactory;
import by.lozovenko.xmlparser.factory.ParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String FILEPATH = "src/main/resources/data/tariffs.xml";

    public static void main(String[] args) throws ProjectException {
        ParserType parser = ParserType.STAX;
        AbstractTariffBuilder tariffBuilder = BuilderFactory.createTariffBuilder(parser);
        tariffBuilder.buildTariffs(FILEPATH);
        LOGGER.info(tariffBuilder.getTariffs());
    }
}
