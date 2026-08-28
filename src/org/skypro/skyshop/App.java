package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        basket.addProduct(new SimpleProduct("Хлеб", 30));
        basket.addProduct(new DiscountedProduct("Молоко", 100, 20));
        basket.addProduct(new FixPriceProduct("Ручка"));
        basket.addProduct(new SimpleProduct("Яблоко", 50));

        System.out.println("Содержимое корзины");
        basket.printContents();
        System.out.println("Общая стоимость:" + basket.getTotalPrice());
        System.out.println("Есть ли 'молоко'?" + basket.containsProduct("Молоко"));
        System.out.println("Есть ли 'Сок'?" + basket.containsProduct("Сок"));

        basket.clear();
        System.out.println("\nПосле очистки;");
        basket.printContents();
    }
}




