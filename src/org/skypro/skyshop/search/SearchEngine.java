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
public Searchable findBestMatch(String search)throws BestResultNotFound{
    Searchable bestResult=null;
    int maxCount=0;

    for(Searchable item: items){
        if (item != null) {
            String term=item.getSearchTerm();
            int count=countOccurrences(term,search);
            if (count>maxCount){
                maxCount=count;
                bestResult=item;
            }
        }
    }
    if (bestResult==null){
        throw new BestResultNotFound(search);
    }
    return bestResult;
    }
    private int countOccurrences(String source,String search){
        if (search==null||search.isEmpty()){
            return 0;
        }
        int count=0;
        int index=0;
        int sublndex=source.indexOf(search,index);
        while (sublndex!=-1){
            count++;
            index=sublndex+search.length();
            sublndex=source.indexOf(search,index);
        }
        return count;
    }
}
