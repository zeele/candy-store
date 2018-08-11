package processOrder.addChocolateProduct;

import shop.ChocolateProduct;

/**
 * IAddChocolateProductCommand - adds to the number of specific chocolate product types
 */
public interface IAddChocolateProductCommand {

    /**
     * Adds to number of specific chocolate product types.
     *
     * @param chocolateProduct
     * @param num
     */
    void execute(ChocolateProduct chocolateProduct, int num);
}
