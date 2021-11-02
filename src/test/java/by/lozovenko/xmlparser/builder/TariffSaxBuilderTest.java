package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.AbstractTariff;
import by.lozovenko.xmlparser.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TariffSaxBuilderTest {
    private AbstractTariff expectedTariff;
    private AbstractTariffBuilder tariffBuilder;

    private static final String XML_FILE_PATH = "src/main/resources/data/test/test1.xml";
    private static final String XSD_FILE_PATH = "data/tariffs.xsd";
    @BeforeClass
    public void setUp() throws ProjectException {
        expectedTariff = TestTariffProvider.createExpectedTariff();

        tariffBuilder = new TariffSaxBuilder();
    }
    @Test
    public void testBuildTariffs() throws ProjectException {
        tariffBuilder.buildTariffs(XML_FILE_PATH);

        AbstractTariff actualTariff = tariffBuilder.getElement(0);
        System.out.println("------ACTUAL------\n"+actualTariff);
        System.out.println("-----EXPECTED-------\n"+expectedTariff);
        assertEquals(actualTariff, expectedTariff);
    }
}