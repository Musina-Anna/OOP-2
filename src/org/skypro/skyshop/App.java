package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Демонстрация корзины (как раньше)
        ProductBasket basket = new ProductBasket();
        basket.addProduct(new SimpleProduct("Хлеб", 30));
        basket.addProduct(new DiscountedProduct("Молоко", 100, 20));
        basket.addProduct(new FixPriceProduct("Ручка"));
        basket.addProduct(new SimpleProduct("Яблоко", 50));

        System.out.println("Содержимое корзины:");
        basket.printContents();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println();

        // --- Новая часть: поиск ---
        SearchEngine searchEngine = new SearchEngine(10);

        // Добавляем товары
        searchEngine.add(new SimpleProduct("Хлеб", 30));
        searchEngine.add(new DiscountedProduct("Молоко", 100, 20));
        searchEngine.add(new FixPriceProduct("Ручка"));
        searchEngine.add(new SimpleProduct("Яблоко", 50));
        searchEngine.add(new SimpleProduct("Масло", 120));

        // Добавляем статьи
        searchEngine.add(new Article("Как выбрать хлеб", "Хлеб бывает разный: белый, черный, с отрубями."));
        searchEngine.add(new Article("Польза молока", "Молоко содержит кальций, полезно для костей."));
        searchEngine.add(new Article("Секреты яблок", "Яблоки богаты витаминами."));

        // Поиск
        System.out.println("Поиск по 'хлеб':");
        Searchable[] result1 = searchEngine.search("хлеб");
        System.out.println(Arrays.toString(result1));

        System.out.println("\nПоиск по 'молоко':");
        Searchable[] result2 = searchEngine.search("молоко");
        System.out.println(Arrays.toString(result2));

        System.out.println("\nПоиск по 'яблоко':");
        Searchable[] result3 = searchEngine.search("яблоко");
        System.out.println(Arrays.toString(result3));

        System.out.println("\nПоиск по 'кальций':");
        Searchable[] result4 = searchEngine.search("кальций");
        System.out.println(Arrays.toString(result4));

        System.out.println("\nПоиск по 'несуществующее':");
        Searchable[] result5 = searchEngine.search("несуществующее");
        System.out.println(Arrays.toString(result5));
    }
}




