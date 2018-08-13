package shop;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import processOrder.addChocolateProduct.AddDarkChocolateProductCommand;
import processOrder.addChocolateProduct.AddMilkChocolateProductCommand;
import processOrder.addChocolateProduct.AddSugarFreeChocolateProductCommand;
import processOrder.addChocolateProduct.AddWhiteChocolateProductCommand;
import processOrder.addChocolateProduct.IAddChocolateProductCommand;

import java.util.Map;

/**
 * Shopper - Represents a shopper that has placed an
 * order. Shopper has an order, chocolates, and wrappers.
 */
@Data
@Builder
public class Shopper {
    /**
     * {@link Order}
     */
    private final Order order;
    /**
     * The chocolates the shopper has
     */
    private final ChocolateProduct chocolates;
    /**
     * The wrappers the shopper has
     */
    private final ChocolateProduct wrappers;

    /**
     * Adds number to chocolate product type in a chocolate product.
     * E.g. Add 5 to Dark Chocolates
     * E.g. Add 2 to White Wrappers.
     *
     * @param chocolateFlavorType
     * @param chocolateProduct
     * @param num
     */
    public void addToChocolateProduct(ChocolateFlavorType chocolateFlavorType, ChocolateProduct chocolateProduct, int num) {
        IAddChocolateProductCommand command = Shopper.chocolateTypeToAddCommandMap
                .get(chocolateFlavorType);

        command.execute(chocolateProduct, num);
    }

    /**
     * Adds number to chocolate product type in multiple chocolate products.
     * E.g. Add 5 to
     *  - Dark Chocolates
     *  - Dark Wrappers
     *
     * @param chocolateFlavorType
     * @param num
     */
    public void addToChocolateProducts(ChocolateFlavorType chocolateFlavorType, int num) {
        addToChocolateProduct(chocolateFlavorType, this.chocolates, num);
        addToChocolateProduct(chocolateFlavorType, this.wrappers, num);
    }

    /**
     * A map of the ChocolateFlavorType to a command to add a chocolate for that type.
     */
    public static final Map<ChocolateFlavorType, IAddChocolateProductCommand> chocolateTypeToAddCommandMap =
            ImmutableMap.of(ChocolateFlavorType.MILK, new AddMilkChocolateProductCommand(),
                    ChocolateFlavorType.DARK, new AddDarkChocolateProductCommand(),
                    ChocolateFlavorType.WHITE, new AddWhiteChocolateProductCommand(),
                    ChocolateFlavorType.SUGAR_FREE, new AddSugarFreeChocolateProductCommand());

    /**
     * Using a chocolate product (e.g. chocolates or wrappers), return a map that maps the
     * flavor of chocolate (Milk, Dark..) to their counts.
     *
     * @param chocolateProduct
     * @return a map of chocolate product type and the number of chocolate products
     */
    public static Map<ChocolateFlavorType, Integer> getCountByFlavor(ChocolateProduct chocolateProduct) {
        Map<ChocolateFlavorType, Integer> chocolateProductCountByType = Maps.newHashMap();
        chocolateProductCountByType.put(ChocolateFlavorType.MILK,
                chocolateProduct.getMilk());
        chocolateProductCountByType.put(ChocolateFlavorType.DARK,
                chocolateProduct.getDark());
        chocolateProductCountByType.put(ChocolateFlavorType.WHITE,
                chocolateProduct.getWhite());
        chocolateProductCountByType.put(ChocolateFlavorType.SUGAR_FREE,
                chocolateProduct.getSugarFree());
        return chocolateProductCountByType;
    }
}
