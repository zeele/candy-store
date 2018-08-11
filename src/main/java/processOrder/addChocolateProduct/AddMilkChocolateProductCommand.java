package processOrder.addChocolateProduct;

import shop.ChocolateProduct;

/**
 * {@inheritDoc}
 */
public class AddMilkChocolateProductCommand implements IAddChocolateProductCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(ChocolateProduct chocolateProduct, int num) {
        chocolateProduct.addMilk(num);
    }
}
