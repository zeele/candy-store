package parser;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import shop.ChocolateFlavorType;
import shop.ChocolateProduct;
import shop.Order;
import shop.Shopper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

/**
 * CSVParser - Contains utility functions to work with Chocolate store specific
 * CSV inputs and outputs.
 */
public class CSVParser {

    private static final Logger logger = Logger.getLogger(
            CSVParser.class.getName());

    /**
     * Record headers expected for input file
     */
    private enum CSVRecordHeader {
        CASH("cash"),
        PRICE("price"),
        WRAPPERS_NEEDED("wrappers needed"),
        TYPE("type");

        private final String label;

        CSVRecordHeader(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    /**
     * Gets value from record by header, returns an int. Exception is not caught
     * here as program should exit.
     *
     * @param CSVRecordHeader
     * @param record
     * @return the integer value from the field in the record
     */
    private static Integer getIntValueFromRecord(CSVRecordHeader CSVRecordHeader, CSVRecord record) {
        return Integer.parseInt(record.get(CSVRecordHeader.getLabel()));
    }

    /**
     * Get CSVRecords iterable from input file.
     * Creates orders by iterating through the CSV records. Instantiates
     * shopper objects with an order, chocolates and chocolate wrappers.
     * Returns list of instantiated shopper objects.
     *
     * @param inputFileName
     * @return List of shoppers
     * @throws IOException
     */
    public static List<Shopper> getListOfShoppers(String inputFileName) throws IOException {
        //Get CSV Records
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String fileUrl = classLoader.getResource(inputFileName).getFile();
        Reader in = new FileReader(new File(fileUrl));

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(String.valueOf(EnumSet.allOf(CSVRecordHeader.class)))
                .withFirstRecordAsHeader()
                .parse(in);

        //Create List of shoppers
        List<Shopper> shoppers = Lists.newArrayList();

        for (CSVRecord record : records) {
            Order order = Order.builder()
                    .cash(getIntValueFromRecord(CSVRecordHeader.CASH, record))
                    .price(getIntValueFromRecord(CSVRecordHeader.PRICE, record))
                    .wrappersNeeded(getIntValueFromRecord(CSVRecordHeader.WRAPPERS_NEEDED, record))
                    .chocolateFlavorType(ChocolateFlavorType.fromString(
                            record.get(CSVRecordHeader.TYPE.getLabel())))
                    .build();
            Shopper shopper = Shopper.builder()
                    .order(order)
                    .chocolates(new ChocolateProduct())
                    .wrappers(new ChocolateProduct())
                    .build();
            shoppers.add(shopper);
        }

        return shoppers;
    }

    /**
     * Creates a CSV file with order information.
     * todo: Output file location is hardcoded, change in future.
     *
     * @param shoppers
     * @throws IOException
     */
    public static void createCSVFileWithOrders(List<Shopper> shoppers) throws IOException {
        FileWriter out = new FileWriter("out/output.parser");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            shoppers.forEach(shopper -> {
                ChocolateProduct chocolates = shopper.getChocolates();
                try {
                    printer.printRecord(
                            "milk " + chocolates.getMilk(),
                            "dark " + chocolates.getDark(),
                            "white " + chocolates.getWhite(),
                            "sugar free " + chocolates.getSugarFree());
                } catch (IOException e) {
                    logger.severe(String.format("Error writing record to CSV %s", e));
                }
            });
        }
    }

}
