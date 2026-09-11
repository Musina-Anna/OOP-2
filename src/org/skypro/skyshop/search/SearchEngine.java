package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[]items;
    private int size=0;

    public SearchEngine(int capacity){
        items=new Searchable[capacity];
    }
    public void add(Searchable item){
        if (size< items.length) {
            items[size] = item;
            size++;
        }else {
            System.out.println("Поисковая система переполнена");
        }
    }
    public Searchable[]search(String query){
        Searchable[]results=new Searchable[5];
        int found=0;
        String lowerQuery=query.toLowerCase();
        for (int i = 0; i < items.length&&found<5; i++) {
            Searchable item=items[i];
            if (item!=null){
                String term=item.getSearchTerm().toLowerCase();
                if (term.contains(lowerQuery)){
                    results[found]=item;
                    found++;
                }
            }
        }
        return results;
    }
}
