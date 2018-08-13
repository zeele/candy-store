package shop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * ChocolateProduct - represents a Chocolate Product
 * (E.g. Chocolates, or chocolate wrappers)
 */
@Data
@AllArgsConstructor
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

}
