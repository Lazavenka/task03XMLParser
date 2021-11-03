package by.lozovenko.xmlparser.util;

import java.math.BigDecimal;

public class XmlParserUtil {
    public static BigDecimal stringToBigDecimal(String value) {
        double number = Double.parseDouble(value);
        return BigDecimal.valueOf(number);
    }
}
