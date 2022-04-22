package sorting;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineDataType extends DataTypeStatsGenerator<String> {

    @Override
    void printStats(SortingType sortingType) {

        //Input from user
        List<String> outList = getUserInput(Pattern.compile("\n"));
        System.out.printf("Total words: %d.\n", outList.size());

        if (sortingType == SortingType.BY_COUNT) {
            List<Map.Entry<String, Integer>> sortedByCount = sortListByCount(outList, comparatorIntValueThenStringKey());
            printStatsByCount(sortedByCount);
        } else {
            //NATURAL
            List<String> sortedReverse = sortNaturalOrder(outList);
            printStatsByNaturalOrder(sortedReverse, Pattern.compile("\n"));
        }
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
}
