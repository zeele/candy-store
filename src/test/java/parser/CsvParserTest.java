package parser;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import shop.ChocolateFlavorType;
import shop.Shopper;
import testUtils.ShopperBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserTest {

    @Test
    void getListOfShoppersTest() throws IOException {
        List<Shopper> shoppers = Lists.newArrayList();

        shoppers.add(ShopperBuilder.newShopperWithOrder(12,2,5,ChocolateFlavorType.MILK));
        shoppers.add(ShopperBuilder.newShopperWithOrder(12,4,4,ChocolateFlavorType.DARK));
        shoppers.add(ShopperBuilder.newShopperWithOrder(6,2,2,ChocolateFlavorType.SUGAR_FREE));
        shoppers.add(ShopperBuilder.newShopperWithOrder(6,2,2,ChocolateFlavorType.WHITE));

        assertEquals(shoppers, CSVParser.getListOfShoppers("input.csv"));
    }

    @Test
    void createCSVFileWithOrdersTest() throws IOException {
        List<Shopper> shoppers = Lists.newArrayList();

        shoppers.add(ShopperBuilder.newShopperWithChocolates(1,2,3,4));
        shoppers.add(ShopperBuilder.newShopperWithChocolates(1,2,3,4));

        CSVParser.createCSVFileWithOrders(shoppers, "output.csv");
        File file = new File("C:\\stride\\output.csv");
        Reader in = new FileReader(file);

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);

        for (CSVRecord record : records) {
            assertEquals("milk 1", record.get(0));
            assertEquals("dark 2", record.get(1));
            assertEquals("white 3", record.get(2));
            assertEquals("sugar free 4", record.get(3));
        }

        in.close();
        file.delete();
    }
}
