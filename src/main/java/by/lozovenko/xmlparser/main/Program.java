package by.lozovenko.xmlparser.main;

import by.lozovenko.xmlparser.builder.AbstractTariffBuilder;
import by.lozovenko.xmlparser.exception.ProjectException;
import by.lozovenko.xmlparser.factory.BuilderFactory;
import by.lozovenko.xmlparser.factory.ParserType;

public class Program {
    public static void main(String[] args) throws ProjectException {
        ParserType parser = ParserType.DOM;
        AbstractTariffBuilder tariffBuilder = BuilderFactory.createTariffBuilder(parser);
        tariffBuilder.buildTariffs("src/main/resources/data/tariffs.xml");
        System.out.println(tariffBuilder.getTariffs());
    }
}
