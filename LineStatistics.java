package sorting;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineStatistics extends Statistics<String> {


    public LineStatistics(SortingType sortingType) {
        super(sortingType);
    }

    @Override
    void compute() {

        List<String> outList = getUserInput(Pattern.compile("\n"));

        //Print total number of items in the list
        System.out.printf("Total words: %d.\n", outList.size());

        //Sort and print stats BY_COUNT.
        // END if true.
        if (super.sortingType == SortingType.BY_COUNT) {
            List<Map.Entry<String, Integer>> sortedByCount = sortListByCount(outList, comparatorIntValueThenLineKey());
            printStatsByCount(sortedByCount);
            return;
        }

        //Sort and prints NATURAL
        //Print summary REVERSED order (longer -> shorter)
        List<String> sortedReverse = sortNaturalOrder(outList);
        printStatsByNaturalOrder(sortedReverse, Pattern.compile("\n"));
    }

    @Override
    List<String> sortNaturalOrder(List<String> unsorted) {
        return unsorted
                .stream()
                .sorted(Comparator
                        .comparingInt(String::length)
                        .reversed())
                .collect(Collectors.toList());
    }

    public Comparator<Map.Entry<String, Integer>> comparatorIntValueThenLineKey(){
        return new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int compVal = Integer.compare(o1.getValue(),(o2.getValue()));
                if (compVal == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                    //return Integer.compare(o2.getKey().length(),(o1.getKey().length()));
                } else {
                    return compVal;
                }
            }
        };
    }
}
