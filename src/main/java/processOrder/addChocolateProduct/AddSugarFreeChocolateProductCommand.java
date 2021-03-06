package processOrder.addChocolateProduct;

import shop.ChocolateProduct;

/**
 * {@inheritDoc}
 */
public class AddSugarFreeChocolateProductCommand implements IAddChocolateProductCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(ChocolateProduct chocolateProduct, int num) {
        chocolateProduct.addSugarFree(num);
    }
}
