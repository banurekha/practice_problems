import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class SalesTaxTest {
    SalesTax salesTax;

    @Before
    public void setUp(){
        double BASICSALESTAX = 0.1;
        List<String> exemptGoods = newArrayList("book", "books", "chocolate", "chocolates", "headache pills");
        double IMPORTTAX = 0.05;
        salesTax = new SalesTax(BASICSALESTAX, exemptGoods, IMPORTTAX);
    }

    @Test
    public void shouldCalculateTenPercentBasicSalesTaxOnNonExemptGoods(){
        InputLine inputLine = new InputLine(1,"hat", 100.00);
        double expectedBasicSalesTax = 10.00;
        double actualBasicSalesTax = salesTax.getBasicSalesTax(inputLine);
        assertEquals(expectedBasicSalesTax, actualBasicSalesTax, 0);
    }

    @Test
    public void shouldNotCalculateBasicSalesTaxForExemptGoods(){
        InputLine inputLine = new InputLine(1,"book", 100.00);
        double expectedBasicSalesTax = 0.00;
        double actualBasicSalesTax = salesTax.getBasicSalesTax(inputLine);
        assertEquals(expectedBasicSalesTax, actualBasicSalesTax, 0);
    }

    @Test
    public void shouldCalculateImportDutyForAllImportedGoods(){
        InputLine inputLine = new InputLine(1, "imported box of chocolates", 10.00);
        double expectedImportDuty = 0.50;
        double actualImportDuty = salesTax.getImportDuty(inputLine);
        assertEquals(expectedImportDuty, actualImportDuty, 0);
    }

    @Test
    public void shouldNotCalculateImportDutyForLocalGoods(){
        InputLine inputLine = new InputLine(1,"book", 100.00);
        double expectedImportDuty = 0.00;
        double actualImportDuty = salesTax.getImportDuty(inputLine);
        assertEquals(expectedImportDuty, actualImportDuty, 0);
    }

}