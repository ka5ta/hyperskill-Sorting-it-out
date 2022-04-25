package sorting;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordDataType extends DataTypeStatsGenerator<String> {

    @Override
    public String generateStats(String data, SortingType sortingType) {

        //Get list of words from text
        List<String> outList = getDataFromText(data, Pattern.compile("\\s+"));

        if (sortingType == SortingType.BY_COUNT) {
            List<Map.Entry<String, Integer>> sortedStringsByCount = sortListByCount(outList,
                    comparatorIntValueThenStringKey());
            return getStatsByCount(sortedStringsByCount, outList.size());
        } else {
            //NATURAL (shorter -> longer)
            List<String> sortedStringNatural = sortNaturalOrder(outList);
            return getStatsByNaturalOrder(sortedStringNatural, Pattern.compile(" "));
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




