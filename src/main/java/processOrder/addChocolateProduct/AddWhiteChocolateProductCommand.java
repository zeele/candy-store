package processOrder.addChocolateProduct;

import shop.ChocolateProduct;

/**
 * {@inheritDoc}
 */
public class AddWhiteChocolateProductCommand implements IAddChocolateProductCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(ChocolateProduct chocolateProduct, int num) {
        chocolateProduct.addWhite(num);
    }
}
