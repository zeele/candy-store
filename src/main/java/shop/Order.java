package shop;

import lombok.Builder;
import lombok.Data;

/**
 * Order - Represents an order that is placed. Immutable.
 */
@Data
@Builder
public class Order {
    /**
     * The amount of cash the shopper has to spend on chocolates.
     */
    private final int cash;
    /**
     * The price of a single chocolates.
     */
    private final int price;
    /**
     * The number of wrappers that must be traded in, in order to receive
     * free chocolates. This number applies to all chocolateProducts irrespective
     * of its type.
     */
    private final int wrappersNeeded;
    /**
     * The type of chocolate flavor the shopper is buying in the order.
     */
    private final ChocolateFlavorType chocolateFlavorType;
}
