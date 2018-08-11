package parser;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import shop.ChocolateFlavorType;
import shop.ChocolateProduct;
import shop.Order;
import shop.Shopper;

import java.io.IOException;
import java.util.List;

class CsvParserTest {

//    @ParameterizedTest(name = "{0}")
//    @DisplayName("")
//    @MethodSource("")


    @ParameterizedTest
    void getListOfShoppersTest() throws IOException {

        List<Shopper> expected = Lists.newArrayList();

        expected.add(Shopper.builder()
                .order(Order.builder()
                        .cash(12).price(5).wrappersNeeded(5)
                        .chocolateFlavorType(ChocolateFlavorType.MILK)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build());

        expected.add(Shopper.builder()
                .order(Order.builder()
                        .cash(12).price(4).wrappersNeeded(4)
                        .chocolateFlavorType(ChocolateFlavorType.DARK)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build());

        expected.add(Shopper.builder()
                .order(Order.builder()
                        .cash(6).price(2).wrappersNeeded(2)
                        .chocolateFlavorType(ChocolateFlavorType.SUGAR_FREE)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build());

        expected.add(Shopper.builder()
                .order(Order.builder()
                        .cash(6).price(2).wrappersNeeded(2)
                        .chocolateFlavorType(ChocolateFlavorType.WHITE)
                        .build())
                .chocolates(new ChocolateProduct())
                .wrappers(new ChocolateProduct())
                .build());

        Assert.assertEquals(CSVParser.getListOfShoppers("input.csv"), expected);
    }

//    private static Stream<Arguments> () {
//
//        return Stream.of(
//                Arguments.of("")
//        );
//    }
}
