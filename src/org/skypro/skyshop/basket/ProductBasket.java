package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        String name = product.getName();
        if (!products.containsKey(name)) {
            products.put(name, new LinkedList<>());
        }
        products.get(name).add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                total += p.getPrice();
            }
        }
        return total;
    }

    public void printContents() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        int specialCount = 0;
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                System.out.println(p.toString());
                if (p.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого:" + getTotalPrice());
        System.out.println("Специальных товаров:" + specialCount);
    }


    public boolean containsProduct(String name) {
        return products.containsKey(name) && !products.get(name).isEmpty();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new LinkedList<>();
        if (!products.containsKey(name)) {
            return removed;
        }
        List<Product> list = products.get(name);
        Iterator<Product> iterator = list.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            removed.add(p);
            iterator.remove();
        }
        if (list.isEmpty()) {
            products.remove(name);
        }
        return removed;
    }

    public void clear() {
        products.clear();
    }
}






