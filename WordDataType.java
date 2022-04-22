package sorting;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordDataType extends DataTypeStatsGenerator<String> {

    @Override
    public void printStats(SortingType sortingType) {

        //Input from user
        List<String> outList = getUserInput(Pattern.compile("\\s+"));
        System.out.printf("Total words: %d.\n", outList.size());


        if (sortingType == SortingType.BY_COUNT) {
            List<Map.Entry<String, Integer>> sortedStringsByCount = sortListByCount(outList,
                    comparatorIntValueThenStringKey());
            printStatsByCount(sortedStringsByCount);
        } else {
            //NATURAL (shorter -> longer)
            List<String> sortedStringNatural = sortNaturalOrder(outList);
            printStatsByNaturalOrder(sortedStringNatural, Pattern.compile(" "));
        }
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
}




