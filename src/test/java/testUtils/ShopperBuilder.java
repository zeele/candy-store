package testUtils;

import shop.ChocolateFlavorType;
import shop.ChocolateProduct;
import shop.Order;
import shop.Shopper;

public class ShopperBuilder {

    public static Shopper newShopper() {
        return Shopper.builder()
                .order(Order.builder().build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build();
    }

    public static Shopper newShopperWithOrder(int cash, int price,
                                              int wrappers, ChocolateFlavorType type) {
        return Shopper.builder()
                .order(Order.builder()
                        .cash(cash).price(price).wrappersNeeded(wrappers)
                        .chocolateFlavorType(type)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build();
    }

    public static Shopper newShopperWithChocolates(int milk, int dark,
                                                   int white, int sugarFree) {
        return Shopper.builder()
                .order(Order.builder().build())
                .chocolates(new ChocolateProduct(milk, dark, white, sugarFree))
                .wrappers(new ChocolateProduct())
                .build();
    }

    public static Shopper newShopperWithChocolatesAndWrappers(int milkChocolate, int darkChocolate,
                                                              int whiteChocolate, int sugarFreeChocolate,
                                                              int milkWrapper, int darkWrapper, int whiteWrapper,
                                                              int sugarFreeWrapper) {
        return Shopper.builder()
                .order(Order.builder().build())
                .chocolates(new ChocolateProduct(milkChocolate, darkChocolate, whiteChocolate, sugarFreeChocolate))
                .wrappers(new ChocolateProduct(milkWrapper, darkWrapper, whiteWrapper, sugarFreeWrapper))
                .build();
    }

    public static Shopper newShopperWithWrappersNeededAndWrappers(int wrappersNeeded, int milk,
                                                                  int dark, int white, int sugarFree) {
        return Shopper.builder()
                .order(Order.builder()
                        .cash(0).price(0).wrappersNeeded(wrappersNeeded)
                        .chocolateFlavorType(ChocolateFlavorType.DARK)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct(milk, dark, white, sugarFree))
                .build();
    }
}
