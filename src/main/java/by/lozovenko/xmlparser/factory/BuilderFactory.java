package by.lozovenko.xmlparser.factory;

import by.lozovenko.xmlparser.builder.AbstractTariffBuilder;
import by.lozovenko.xmlparser.builder.TariffDomBuilder;
import by.lozovenko.xmlparser.builder.TariffSaxBuilder;
import by.lozovenko.xmlparser.builder.TariffStaxBuilder;

public class BuilderFactory {
    private enum ParserType{
        SAX, STAX, DOM
    }
    private BuilderFactory(){
    }

    public static AbstractTariffBuilder createTariffBuilder(String parserType) throws IllegalArgumentException{
        ParserType parser = ParserType.valueOf(parserType.toUpperCase());
        AbstractTariffBuilder tariffBuilder;
        switch (parser){
            case DOM -> tariffBuilder = new TariffDomBuilder();
            case SAX -> tariffBuilder = new TariffSaxBuilder();
            case STAX -> tariffBuilder = new TariffStaxBuilder();
            default -> throw new EnumConstantNotPresentException(parser.getDeclaringClass(), parser.name());
        }
        return tariffBuilder;
    }
}
