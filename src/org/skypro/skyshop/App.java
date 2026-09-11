package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("===Проверка валидации===");
        try {
            new SimpleProduct("", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка:" + e.getMessage());
        }

        try {
            new SimpleProduct(" ", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            new SimpleProduct("Хлеб", -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            new DiscountedProduct("Молоко", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            new DiscountedProduct("Молоко", 0, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();

        SearchEngine searchEngine = new SearchEngine(10);



        // Добавляем товары
        searchEngine.add(new SimpleProduct("Хлеб", 30));
        searchEngine.add(new DiscountedProduct("Молоко", 100, 20));
        searchEngine.add(new FixPriceProduct("Ручка"));
        searchEngine.add(new Article("Как выбрать хлеб", "Хлеб бывает разный: белый, черный, с отрубями."));
        searchEngine.add(new Article("Польза молока", "Молоко содержит кальций, полезно для костей."));

        System.out.println("===Поиск лучшего совпадения для 'хлеб'===");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("хлеб");
            System.out.println("Найден лучший результат:" + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n===Поиск лучшего совпадения для 'шоколад'===");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("шоколад");
            System.out.println("Найден лучший результат:" + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Исключение:" + e.getMessage());
        }
        System.out.println("\n===Старый поиск(до 5 элементов) для 'молоко'===");
        Searchable[] results = searchEngine.search("молоко");
        for (Searchable s : results) {
            if (s != null) {
                System.out.println(s.getStringRepresentation());
            }
        }
    }
}







