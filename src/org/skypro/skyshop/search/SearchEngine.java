package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items = new LinkedList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public List<Searchable> search(String query) {
        List<Searchable> results = new LinkedList<>();
        String lowerQuery = query.toLowerCase();

        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String search)throws BestResultNotFound{
    Searchable bestResult=null;
    int maxCount=0;

    for (Searchable item:items){
        if (item!=null){
            int count=countOccurrences(item.getSearchTerm(),search);
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
        if (search==null||search.isEmpty())return 0;
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


