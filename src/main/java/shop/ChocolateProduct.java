package shop;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Data;
import processOrder.addChocolateProduct.AddDarkChocolateProductCommand;
import processOrder.addChocolateProduct.AddMilkChocolateProductCommand;
import processOrder.addChocolateProduct.AddSugarFreeChocolateProductCommand;
import processOrder.addChocolateProduct.AddWhiteChocolateProductCommand;
import processOrder.addChocolateProduct.IAddChocolateProductCommand;

import java.util.Map;

/**
 * ChocolateProduct - represents a Chocolate Product
 * (E.g. Chocolates, or chocolate wrappers)
 */
@Data
public class ChocolateProduct {
    private int milk;
    private int dark;
    private int white;
    private int sugarFree;

    public ChocolateProduct() {
        this.milk = 0;
        this.dark = 0;
        this.white = 0;
        this.sugarFree = 0;
    }

    public void addMilk(int num) {
        this.milk += num;
    }

    public void addDark(int num) {
        this.dark += num;
    }

    public void addWhite(int num) {
        this.white += num;
    }

    public void addSugarFree(int num) {
        this.sugarFree += num;
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
