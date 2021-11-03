package by.lozovenko.xmlparser.builder;

import by.lozovenko.xmlparser.entity.AbstractTariff;
import by.lozovenko.xmlparser.exception.ProjectException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TariffStaxBuilderTest {
    private AbstractTariff expectedTariff;
    private AbstractTariffBuilder tariffBuilder;

    private static final String XML_FILE_PATH = "src/main/resources/data/test/test1.xml";

    @BeforeClass
    public void setUp(){
        expectedTariff = TestTariffProvider.createExpectedTariff();

        tariffBuilder = new TariffStaxBuilder();
    }
    @Test
    public void testBuildTariffs() throws ProjectException {
        tariffBuilder.buildTariffs(XML_FILE_PATH);

        AbstractTariff actualTariff = tariffBuilder.getElement(0);

        assertEquals(actualTariff, expectedTariff);
    }
}