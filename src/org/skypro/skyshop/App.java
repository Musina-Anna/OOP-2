package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("===Демонстрация корзины===");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(new SimpleProduct("Хлеб", 30));
        basket.addProduct(new DiscountedProduct("Молоко", 100, 20));
        basket.addProduct(new FixPriceProduct("Ручка "));
        basket.addProduct(new SimpleProduct("Яблоко", 50));

        basket.addProduct(new SimpleProduct("Сыр", 200));
        basket.addProduct(new SimpleProduct("Хлеб", 35));

        basket.printContents();


        System.out.println("\n===Удаление продукта 'Хлеб'===");
        List<Product> removed = basket.removeProductByName("Хлеб");
        if (removed.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удаленные продукты:");
            for (Product p : removed) {
                System.out.println(p.toString());
            }
        }
        System.out.println("Содержимое корзины после удаления:");
        basket.printContents();

        System.out.println("\n===Удаление несуществующего продукта 'Шоколад'===");
        List<Product> removedEmpty = basket.removeProductByName("Шоколад");
        if (removedEmpty.isEmpty()) {
            System.out.println("Список пуст");
        }
        System.out.println("Содержимое корзины");
        basket.printContents();


        System.out.println("\n===Демонстрация поиска===");
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(new SimpleProduct("Хлеб", 30));
        searchEngine.add(new DiscountedProduct("Молоко", 100, 20));
        searchEngine.add(new FixPriceProduct("Ручка"));
        searchEngine.add(new Article("Как выбрать хлеб", "Хлеб бывает разный.Белый хлеб и черный хлеб."));
        searchEngine.add(new Article("Польза молока", "Молоко содержит кальций. Молоко полезно"));

        System.out.println("Поиск по 'хлеб':");
        Set<Searchable> results = searchEngine.search("хлеб");
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Searchable s : results) {
                System.out.println(s.getStringRepresentation());
            }
        }

        System.out.println("\nПоиск лучшего совпадения для 'молоко':");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("молоко");
            System.out.println("Лучший результат:" + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение:" + e.getMessage());
        }
    }
}







