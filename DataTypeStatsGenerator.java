package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @param <T> specific to the extending class type
 *           in project its <String> or <Long>
 */

public abstract class DataTypeStatsGenerator<T>{

    abstract String generateStats(String data, SortingType sortingType);
    abstract List<T> sortNaturalOrder(List<T> unsorted);

    public static String getStatisticsByType(String data, String dataType, SortingType sortingType) {
        if (dataType == null) {
            throw new RuntimeException("No data type defined!");
        }
        switch (dataType) {
            case "long":
                return new LongDataType().generateStats(data, sortingType);
            case "line":
                return new LineDataType().generateStats(data, sortingType);
            case "word":
                return new WordDataType().generateStats(data, sortingType);
            default:
                System.out.println("No data type defined!");
                break;
        }
        throw new RuntimeException("Statistics could not be generated");
    }


    public List<String> getDataFromText(String data, Pattern delimiter){
        try(Scanner scanner = new Scanner(data)) {
            return scanner
                    .useDelimiter(delimiter)
                    .tokens().collect(Collectors.toList());
        }
    }

    public <K extends Comparable<K>> List<Map.Entry<K, Integer>> sortListByCount(List<K> listToSort,
                                                                                 Comparator<Map.Entry<K, Integer>> comparator) {
        Map<K, Integer> occurrenceMap = getKeyOccurrence(listToSort);

       return occurrenceMap.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Comparator<Map.Entry<String, Integer>> comparatorIntValueThenStringKey(){
        return new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int compVal = Integer.compare(o1.getValue(),(o2.getValue()));
                if (compVal == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return compVal;
                }
            }
        };
    }

    private <K extends Comparable<? super K>> Map<K, Integer> getKeyOccurrence(List<K> listToSort){

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


    // PRINT METHODS:
    public <V> String getStatsByNaturalOrder(List<V> sortedList, Pattern delimiter){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total words: %d.\n", sortedList.size()));
        sb.append(String.format("Sorted data:%s%s", delimiter, convertListItemsToText(sortedList, delimiter)));
        //System.out.println(sb);
        return sb.toString();
    }

    private static <T> String convertListItemsToText(List<T> listToPrint, Pattern delimiter) {
        StringBuilder sb = new StringBuilder();
        listToPrint.forEach(i -> sb.append(i).append(delimiter));
        return sb.toString();
    }

    public <K> String getStatsByCount(List<Map.Entry<K, Integer>> entryList, int dataSize){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total words: %d.\n", dataSize));

        entryList.forEach(e -> {
            int percentage = getPercentage(e.getValue(), dataSize);
            sb.append(String.format("%s: %d time(s), %s%s\n", e.getKey(), e.getValue(), percentage, "%"));
        });
        //System.out.println(sb);
        return sb.toString();
    }

    private int getPercentage(int maxOccurrence, int size) {
        double percentage = Math.round(maxOccurrence * 100 / (double) size);
        return (int) percentage;
    }
}
