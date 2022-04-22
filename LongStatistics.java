package sorting;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LongStatistics extends Statistics<Long> {

    public LongStatistics(SortingType sortingType) {
        super(sortingType);
    }

    @Override
    void compute() {

        // Get list from user input
        List<String> outList = getUserInput(Pattern.compile("\\s+"));
        // Convert Strings list to Longs
        List<Long> longs = convertStringsToLongs(outList);


        //Sort and print stats BY_COUNT.
        //END
        if (super.sortingType == SortingType.BY_COUNT) {
            System.out.printf("Total numbers: %d.\n", longs.size());
            List<Map.Entry<Long, Integer>> sortedByCount = sortListByCount(longs, comparatorIntValueLongKey());
            printStatsByCount(sortedByCount);
            return;
        }

        //Sort and prints NATURAL
        //Print summary by SortingType: Natural (smaller -> bigger)
        List<Long> sortedNatural = sortNaturalOrder(longs);
        System.out.printf("Total numbers: %d.\n", longs.size());
        printStatsByNaturalOrder(sortedNatural, Pattern.compile(" "));
    }


    private List<Long> convertStringsToLongs(List<String> strings){
        List<Long> longs = new ArrayList<>();
        for (String item : strings) {
            try {
                longs.add(Long.parseLong(item));
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" is not a long. It will be skipped\n", item);
            }
        }
        return longs;
    }


    private Comparator<Map.Entry<Long, Integer>> comparatorIntValueLongKey(){
        return new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                int compValue = Integer.compare(o1.getValue(), o2.getValue());
                if(compValue == 0){
                    return Long.compare(o1.getKey(), o2.getKey());
                } else {
                    return compValue;
                }
            }
        };
    }

    @Override
    List<Long> sortNaturalOrder(List<Long> unsorted) {
        return unsorted
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}


