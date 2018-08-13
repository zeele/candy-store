package controller;

import parser.CSVParser;
import processOrder.OrderProcessor;
import shop.Shopper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ChocolateShopController {

    public static void main(String... args) throws IOException {

        List<Shopper> shoppers;

        final Logger logger = Logger.getLogger(ChocolateShopController.class.getName());

        shoppers = CSVParser.getListOfShoppers("input.csv");

        if (shoppers != null) {
            shoppers.forEach(OrderProcessor::processOrder);
            CSVParser.createCSVFileWithOrders(shoppers, "out/output.csv");
        } else {
            logger.warning("Error processing orders from file, or file does not contain orders.");
        }
    }

}
