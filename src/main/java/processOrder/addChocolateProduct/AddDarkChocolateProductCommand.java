package processOrder.addChocolateProduct;

import shop.ChocolateProduct;

/**
 * {@inheritDoc}
 */
public class AddDarkChocolateProductCommand implements IAddChocolateProductCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(ChocolateProduct chocolateProduct, int num) {
        chocolateProduct.addDark(num);
    }
}
