package shop;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * ChocolateFlavorType - Represents the type of Chocolate flavor
 */
public enum ChocolateFlavorType {
    MILK("milk") {
        @Override
        public Map<ChocolateFlavorType, Integer> getPromotionRules() {
            Map<ChocolateFlavorType, Integer> complimentaryChocolateProduct = Maps.newHashMap();
            complimentaryChocolateProduct.put(ChocolateFlavorType.MILK, 1);
            complimentaryChocolateProduct.put(ChocolateFlavorType.SUGAR_FREE, 1);
            return complimentaryChocolateProduct;
        }
    },
    DARK("dark") {
        @Override
        public Map<ChocolateFlavorType, Integer> getPromotionRules() {
            Map<ChocolateFlavorType, Integer> complimentaryChocolateProduct = Maps.newHashMap();
            complimentaryChocolateProduct.put(ChocolateFlavorType.DARK, 1);
            return complimentaryChocolateProduct;
        }
    },
    WHITE("white") {
        @Override
        public Map<ChocolateFlavorType, Integer> getPromotionRules() {
            Map<ChocolateFlavorType, Integer> complimentaryChocolateProduct = Maps.newHashMap();
            complimentaryChocolateProduct.put(ChocolateFlavorType.WHITE, 1);
            complimentaryChocolateProduct.put(ChocolateFlavorType.SUGAR_FREE, 1);
            return complimentaryChocolateProduct;
        }
    },
    SUGAR_FREE("sugar free") {
        @Override
        public Map<ChocolateFlavorType, Integer> getPromotionRules() {
            Map<ChocolateFlavorType, Integer> complimentaryChocolateProduct = Maps.newHashMap();
            complimentaryChocolateProduct.put(ChocolateFlavorType.SUGAR_FREE, 1);
            complimentaryChocolateProduct.put(ChocolateFlavorType.DARK, 1);
            return complimentaryChocolateProduct;
        }
    };

    /**
     * Returns the 'promotion rules' for the type of chocolate flavor
     *
     * @return A map of chocolate flavor type to counts
     */
    public abstract Map<ChocolateFlavorType, Integer> getPromotionRules();

    private final String label;

    ChocolateFlavorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * A map of chocolate flavor type to labels
     */
    private static ImmutableMap<String, ChocolateFlavorType> lookUpTypeByLabel =
            Maps.uniqueIndex(Arrays.asList(ChocolateFlavorType.values()),
                    ChocolateFlavorType::getLabel);

    /**
     * Reverse lookup of chocolate flavor by the string value.
     *
     * @param chocolateFlavorType map of chocolate flavor to their labels
     * @return type of chocolate flavor
     */
    public static ChocolateFlavorType fromString(final String chocolateFlavorType) {
        return lookUpTypeByLabel.get(chocolateFlavorType);
    }
}
