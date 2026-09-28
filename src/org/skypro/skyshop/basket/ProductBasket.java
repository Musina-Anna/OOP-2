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

    public int getTotalPrice(){
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    private long getSpecialCount(){
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }
    public void printContents(){
        if (products.isEmpty()){
            System.out.println("В корзине пусто");
            return;
        }
        products.values().stream()
                .flatMap(List::stream)
                .forEach(p-> System.out.println(p.toString()));
        System.out.println("Итого: "+getTotalPrice());
        System.out.println("Специальных товаров: "+getSpecialCount());
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






