package sorting;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Statistics<T>{

     SortingType sortingType;

    public Statistics(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    abstract void compute();
    abstract List<T> sortNaturalOrder(List<T> unsorted);


    public List<String> getUserInput(Pattern delimiter){
        return new Scanner(System.in)
                .useDelimiter(delimiter)
                .tokens()
                .collect(Collectors.toList());
    }

    public <K extends Comparable<K>> List<Map.Entry<K, Integer>> sortListByCount(List<K> listToSort,
                                                                                 Comparator<Map.Entry<K, Integer>> comparator) {
        Map<K, Integer> occurrenceMap = getKeyOccurrence(listToSort);

       return occurrenceMap.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public <K extends Comparable<? super K>> Map<K, Integer> getKeyOccurrence(List<K> listToSort){

        Map<K, Integer> occurrenceMap = new HashMap<>();

        for (K item : listToSort) {
            if (occurrenceMap.containsKey(item)) {
                int prevOccurrence = occurrenceMap.get(item);
                occurrenceMap.put(item, prevOccurrence + 1);
            } else {
                occurrenceMap.put(item, 1);
            }
        }
        return occurrenceMap;
    }


    public <V> void printStatsByNaturalOrder(List<V> sortedList, Pattern delimiter){
        System.out.printf("Sorted data:%s%s", delimiter, convertListItemsToText(sortedList, delimiter));
    }

    public static <T> String convertListItemsToText(List<T> listToPrint, Pattern delimiter) {
        StringBuilder sb = new StringBuilder();
        listToPrint.forEach(i -> sb.append(i).append(delimiter));
        return sb.toString();
    }

    public <K> void printStatsByCount(List<Map.Entry<K, Integer>> entryList){
        entryList.forEach(e -> {
            int percentage = getPercentage(e.getValue(), entryList.size());
            System.out.printf("%s: %d time(s), %s%s\n", e.getKey(), e.getValue(), percentage, "%");
        });
    }

    int getPercentage(int maxOccurrence, int size) {
        double percentage = Math.round(maxOccurrence * 100 / (double) size);
        return (int) percentage;
    }
}
