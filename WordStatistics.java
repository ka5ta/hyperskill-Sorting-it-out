package sorting;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordStatistics extends Statistics<String>{

    public WordStatistics(SortingType sortingType) {
        super(sortingType);
    }

    @Override
    public void compute() {

        List<String> outList = getUserInput(Pattern.compile("\\s+"));
        System.out.printf("Total words: %d.\n", outList.size());

        //Sort and print stats BY_COUNT.
        //Check whether input is String or Integer
        // END
        if (super.sortingType == SortingType.BY_COUNT) {
            /*try{
                List<Map.Entry<Integer, Integer>> sortedIntsByCount = sortIntsByCount(outList);
                printStatsByCount(sortedIntsByCount);
            } catch (NumberFormatException e){*/
                List<Map.Entry<String, Integer>> sortedStringsByCount =  sortListByCount(outList, comparatorIntValueThenStringKey());
                printStatsByCount(sortedStringsByCount);
                return;
            }
        //return;
        //}

        //Sort and prints NATURAL
       try {
            //Print summary for Integers: Natural (smaller -> bigger)
            List<Integer> sortedIntNatural = sortIntList(outList);
            printStatsByNaturalOrder(sortedIntNatural, Pattern.compile(" "));
        } catch (NumberFormatException e) {
            //Print summary for Strings: Natural (shorter -> longer)
            List<String> sortedStringNatural = sortNaturalOrder(outList);
            printStatsByNaturalOrder(sortedStringNatural, Pattern.compile(" "));
        }
    }


    private List<Map.Entry<Integer, Integer>> sortIntsByCount(List<String> listToSort) {

        Map<Integer, Integer> occurrenceMap = getKeyOccurrence(convertStringToIntList(listToSort));
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(occurrenceMap.entrySet());

        entryList.sort(comparatorIntValueThenIntKey());
        return entryList;
    }

    private Comparator<Map.Entry<Integer, Integer>> comparatorIntValueThenIntKey() {
        return new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int compVal = Integer.compare(o1.getValue(), (o2.getValue()));
                if (compVal == 0) {
                    int i = Integer.compare(o1.getKey(), (o2.getKey()));
                    return i;
                } else {
                    return compVal;
                }
            }
        };
    }

    public Comparator<Map.Entry<String, Integer>> comparatorIntValueThenStringKey(){
        return new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int compVal = Integer.compare(o1.getValue(),(o2.getValue()));
                if (compVal == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                    //return Integer.compare(o1.getKey().length(),(o2.getKey().length()));
                } else {
                    return compVal;
                }
            }
        };
    }



    @Override
    public List<String> sortNaturalOrder(List<String> unsorted) {
        return unsorted
                .stream()
                .sorted(Comparator
                        .comparingInt(String::length)
                        .thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }

    public List<Integer> sortIntList(List<String> unsorted) {
        return convertStringToIntList(unsorted)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Integer> convertStringToIntList(List<String> lines) {
        List<Integer> intList = new ArrayList<>();
        for (String line : lines) {
            List<Integer> parsedToIntList = Stream.of(line.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            intList.addAll(parsedToIntList);
        }
        return intList;
    }
}



