package by.lozovenko.xmlparser.factory;

import by.lozovenko.xmlparser.builder.AbstractTariffBuilder;
import by.lozovenko.xmlparser.builder.TariffDomBuilder;
import by.lozovenko.xmlparser.builder.TariffSaxBuilder;
import by.lozovenko.xmlparser.builder.TariffStaxBuilder;
import by.lozovenko.xmlparser.exception.ProjectException;

public class BuilderFactory {

    private BuilderFactory() {
    }

    public static AbstractTariffBuilder createTariffBuilder(ParserType parser) throws EnumConstantNotPresentException, ProjectException {
        AbstractTariffBuilder tariffBuilder;
        switch (parser) {
            case DOM -> tariffBuilder = new TariffDomBuilder();
            case SAX -> tariffBuilder = new TariffSaxBuilder();
            case STAX -> tariffBuilder = new TariffStaxBuilder();
            default -> throw new EnumConstantNotPresentException(parser.getDeclaringClass(), parser.name());
        }
        return tariffBuilder;
    }
}
