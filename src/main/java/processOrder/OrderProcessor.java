package processOrder;

import shop.ChocolateFlavorType;
import shop.ChocolateProduct;
import shop.Order;
import shop.Shopper;

import java.util.Map;

/**
 * OrderProcessor - contains methods to process an order.
 * Processing an order means using raw data from an order
 * and calculating the number of Chocolates the shopper gets
 * in the end.
 */
public class OrderProcessor {

    //adding private constructor as this class is not meant to be instantiated
    private OrderProcessor() {}

    /**
     * Processes an order buy doing two actions - buying chocolates
     * and trading in wrappers.
     * <p>
     * Details: If the shopper was unable to buy any chocolates, we
     * do not want to try trading in wrappers as they would have
     * no wrappers. Since trading in wrappers could result in
     * free complimentary chocolates (which results in more
     * wrappers), using a while loop to keep trading in wrappers until
     * we get a signal that there are no more trades possible.
     *
     * @param shopper
     */
    public static void processOrder(Shopper shopper) {
        boolean cont = buyChocolates(shopper);

        while (cont) {
            cont = tradeWrappersForChocolate(shopper);
        }
    }

    /**
     * Use cash shopper has to get chocolates. Adds to their
     * chocolate count and their chocolate wrapper counts. This does
     * not update the cash amount from the order as orders are
     * immutable and we do not need to reference the cash amount
     * after this. Package private for testing
     *
     * @param shopper
     * @return whether the shopper was able to buy chocolates
     */
    static boolean buyChocolates(Shopper shopper) {
        Order order = shopper.getOrder();
        ChocolateFlavorType chocolateType = order.getChocolateFlavorType();

        if (order.getCash() > 0 && order.getCash() >= order.getPrice()) {
            int numOfChocolatesThatCanBeBought = order.getCash() / order.getPrice();
            shopper.addToChocolateProducts(chocolateType, numOfChocolatesThatCanBeBought);
            return true;
        }
        return false;
    }

    /**
     * Trades in wrappers for complimentary chocolates.
     *
     * @param shopper
     * @return whether the shopper was able to trade in wrappers for complimentary chocolates
     */
    static boolean tradeWrappersForChocolate(Shopper shopper) {
        int wrappersNeeded = shopper.getOrder().getWrappersNeeded();
        Map<ChocolateFlavorType, Integer> chocololateWrappersCountByType =
                Shopper.getCountByFlavor(
                        shopper.getWrappers());

        ChocolateFlavorType chocolateFlavorType = getTypeWithAvailableWrappersTestCases(
                chocololateWrappersCountByType, wrappersNeeded);

        if (chocolateFlavorType != null) {
            //deduct the number of wrappers exchanged for complimentary chocolates
            shopper.addToChocolateProduct(chocolateFlavorType, shopper.getWrappers(), -wrappersNeeded);
            //We get the promotion rule for type of chocolate (Milk, Dark..) and pass it
            //to the add function so we know how much to add
            addComplimentaryChocolateProducts(chocolateFlavorType.getPromotionRules(), shopper);
            return true;
        }

        return false;
    }

    /**
     * Get the first chocolate type that has enough wrappers (e.g. required wrappers for this
     * shopper is 2, shopper has 2 milk chocolate wrappers, this will give us ChocolateFlavorType
     * as Milk. If we can't find any type of wrappers that we have enough of, return null.
     *
     * @param chocolateProductTypeToAmount the type of chocolate to the count
     * @param wrappersNeeded
     * @return the type of chocolate product (e.g. Milk, Dark, White..) or null
     */
    static ChocolateFlavorType getTypeWithAvailableWrappersTestCases(
            Map<ChocolateFlavorType, Integer> chocolateProductTypeToAmount, int wrappersNeeded) {

        return chocolateProductTypeToAmount.entrySet().stream()
                .filter(e -> e.getValue() >= wrappersNeeded)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * For each Chocolate Type (Milk, Dark..), we add the amount to the shopper's chocolate products
     *
     * @param promotionRules This is the promotion rule - a map of the of chocolate type to amount of
     *                             complimentary chocolates.
     * @param shopper
     */
    static void addComplimentaryChocolateProducts(Map<ChocolateFlavorType, Integer> promotionRules, Shopper shopper) {
        promotionRules.forEach(shopper::addToChocolateProducts);
    }
}
