package processOrder;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import shop.ChocolateFlavorType;
import shop.ChocolateProduct;
import shop.Shopper;
import testUtils.ShopperBuilder;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderProcessorTest {

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test buy chocolates")
    @MethodSource("buyChocolatesTestCases")
    void buyChocolatesTest(String testName, Shopper shopper, boolean expected) {
        assertEquals(expected, OrderProcessor.buyChocolates(shopper));
    }

    private static Stream<Arguments> buyChocolatesTestCases () {
        return Stream.of(
                Arguments.of("Test buying chocolates, normal case",
                        ShopperBuilder.newShopperWithOrder(12,2,2, ChocolateFlavorType.DARK),
                        true),
                Arguments.of("Test buying chocolates, not enough cash",
                        ShopperBuilder.newShopperWithOrder(5,10,2, ChocolateFlavorType.DARK),
                        false),
                Arguments.of("Test buying chocolates, not enough cash, negative amount",
                        ShopperBuilder.newShopperWithOrder(-2,10,2, ChocolateFlavorType.DARK),
                        false),
                Arguments.of("Test buying chocolates, not enough cash, zero amount",
                        ShopperBuilder.newShopperWithOrder(0,0,2, ChocolateFlavorType.DARK),
                        false)
            );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test trading wrappers for chocolate")
    @MethodSource("tradeWrappersForChocolateTestCases")
    void tradeWrappersForChocolate(String testName, Shopper shopper, boolean expected) throws Exception {
        assertEquals(expected, OrderProcessor.tradeWrappersForChocolate(shopper));
    }

    private static Stream<Arguments> tradeWrappersForChocolateTestCases () {
        return Stream.of(
                Arguments.of("Test trading wrappers, normal case",
                        ShopperBuilder.newShopperWithWrappersNeededAndWrappers(2,0,5,0,0),
                        true),
                Arguments.of("Test trading wrappers, has wrappers, not enough for wrappers needed",
                        ShopperBuilder.newShopperWithWrappersNeededAndWrappers(3,0,0,1,0),
                        false),
                Arguments.of("Test trading wrappers, no wrappers, not enough for wrappers needed",
                        ShopperBuilder.newShopperWithWrappersNeededAndWrappers(1,0,0,0,0),
                        false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test get Candy Type that has available wrappers for trading")
    @MethodSource("getTypeWithAvailableWrappersTestCases")
    void tradeWrappersForChocolate(String testName, Map<ChocolateFlavorType, Integer> countByType,
                                   int wrappersNeeded, ChocolateFlavorType expected) {
        assertEquals(expected,
                OrderProcessor.getTypeWithAvailableWrappersTestCases(countByType, wrappersNeeded));
    }

    private static Stream<Arguments> getTypeWithAvailableWrappersTestCases () {
        Map<ChocolateFlavorType, Integer> countByType = Maps.newHashMap();
        countByType.put(ChocolateFlavorType.MILK, 0);
        countByType.put(ChocolateFlavorType.DARK, 0);
        countByType.put(ChocolateFlavorType.WHITE, 1);
        countByType.put(ChocolateFlavorType.SUGAR_FREE, 0);

        Map<ChocolateFlavorType, Integer> countByType2 = Maps.newHashMap();
        countByType2.putAll(countByType);
        countByType2.put(ChocolateFlavorType.WHITE, 0);
        countByType2.put(ChocolateFlavorType.MILK, 6);

        return Stream.of(
                Arguments.of("Test get Candy Type, normal case", countByType, 1, ChocolateFlavorType.WHITE),
                Arguments.of("Test get Candy Type, normal case", countByType2, 1, ChocolateFlavorType.MILK));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Test add Complimentary Chocolate products")
    @MethodSource("addComplimentaryChocolateProductsTestCases")
    void addComplimentaryChocolateProductsTestCases(String testName, Map<ChocolateFlavorType, Integer> promotionRules, Shopper shopper, Shopper expected) {

        OrderProcessor.addComplimentaryChocolateProducts(promotionRules, shopper);

        ChocolateProduct chocProduct = shopper.getChocolates();
        int milk = chocProduct.getMilk();
        int dark = chocProduct.getDark();
        int white = chocProduct.getWhite();
        int sugarFree = chocProduct.getSugarFree();

        ChocolateProduct chocProductExpected = expected.getChocolates();
        int milkExpected = chocProductExpected.getMilk();
        int darkExpected = chocProductExpected.getDark();
        int whiteExpected = chocProductExpected.getWhite();
        int sugarFreeExpected = chocProductExpected.getSugarFree();

        assertEquals(milkExpected, milk);
        assertEquals(darkExpected, dark);
        assertEquals(whiteExpected, white);
        assertEquals(sugarFreeExpected, sugarFree);
    }

    private static Stream<Arguments> addComplimentaryChocolateProductsTestCases () {
        Map<ChocolateFlavorType, Integer> promotionRules = Maps.newHashMap();
        promotionRules.put(ChocolateFlavorType.MILK, 1);
        promotionRules.put(ChocolateFlavorType.SUGAR_FREE, 1);

        Map<ChocolateFlavorType, Integer> promotionRules2 = Maps.newHashMap();
        promotionRules2.put(ChocolateFlavorType.DARK, 1);

        return Stream.of(
                Arguments.of("Test add Complimentary products, normal case", promotionRules,
                        ShopperBuilder.newShopper(),
                        ShopperBuilder.newShopperWithChocolatesAndWrappers(1,0,0,1,1,0,0,1)),
                Arguments.of("Test add Complimentary products, shopper has existing products", promotionRules2,
                        ShopperBuilder.newShopperWithChocolatesAndWrappers(1,1,1,1,1,1,1,1),
                ShopperBuilder.newShopperWithChocolatesAndWrappers(1,2,1,1,1,2,1,1)));
    }
}
