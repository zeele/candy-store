package shop;

import lombok.Builder;
import lombok.Data;
import processOrder.addChocolateProduct.IAddChocolateProductCommand;

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
        IAddChocolateProductCommand command = ChocolateProduct
                .chocolateTypeToAddCommandMap
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
}
