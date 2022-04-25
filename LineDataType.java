package sorting;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineDataType extends DataTypeStatsGenerator<String> {

    @Override
    public String generateStats(String data, SortingType sortingType) {

        //Get list of words from text
        List<String> outList = getDataFromText(data, Pattern.compile("\n"));

        if (sortingType == SortingType.BY_COUNT) {
            List<Map.Entry<String, Integer>> sortedByCount = sortListByCount(outList, comparatorIntValueThenStringKey());
            return getStatsByCount(sortedByCount, outList.size());
        } else {
            //NATURAL
            List<String> sortedReverse = sortNaturalOrder(outList);
            return getStatsByNaturalOrder(sortedReverse, Pattern.compile("\n"));
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
