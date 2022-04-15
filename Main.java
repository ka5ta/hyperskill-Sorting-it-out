package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {

        try {
            switch (args[1]) {
                case "long":
                    computeLongInput();
                    break;
                case "line":
                    computeLineInput();
                    break;
                case "word":
                    computeWordInput();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            computeWordInput();
        }
    }

    public static void computeLongInput() {

        // Get list of numbers form user
        List<Long> numbers = new Scanner(System.in)
                .useDelimiter("\\s+")
                .tokens()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        //Print total number of items in the list
        int size = numbers.size();
        System.out.printf("Total numbers: %d.\n", size);

        //Get max number and check how many times it occurs
        Long max = Collections.max(numbers);
        int maxOccurrence = (int) numbers.stream()
                .filter(i -> Objects.equals(i, max))
                .count();

        //Count percentage
        int percentage = getPercentage(maxOccurrence, size);

        //Print summary
        System.out.printf("The greatest number: %d (%d time(s), %d%s)\n", max, maxOccurrence, percentage, "%");

    }

    public static void computeLineInput() {

        // Get list of lines form user
        List<String> lines = new Scanner(System.in)
                .useDelimiter("\n")
                .tokens()
                .sorted(Comparator
                        .comparingInt(String::length)
                        .reversed())
                .collect(Collectors.toList());

        //Print total number of lines in the list
        int linesCount = lines.size();
        System.out.printf("Total lines: %d.\n", linesCount);

        //Get list of only longest lines
        int maxLength = lines.get(0).length();
        List<String> longestLines = lines.stream()
                .takeWhile(l -> l.length() == maxLength)
                .collect(Collectors.toList());

        //Count percentage
        int maxOccurrence = longestLines.size();
        int percentage = getPercentage(maxOccurrence, linesCount);

        //Print summary
        if(maxOccurrence == 1){
            System.out.printf("The longest line:\n%s\n(%d time(s), %d%s).\n", longestLines.get(0), maxOccurrence, percentage, "%");
        } else {
            System.out.printf("The longest lines:\n%s\n(%d time(s), %d%s).\n",
                    String.join(", ", longestLines), maxOccurrence, percentage,"%");
        }
    }


    public static void computeWordInput() {

        //Get words from user and sort from longest
        List<String> words = new Scanner(System.in)
                .useDelimiter("\\s+")
                .tokens()
                .sorted(Comparator
                        .comparingInt(String::length)
                        .reversed())
                .collect(Collectors.toList());

        //Print total number of items in the list
        int itemsCount = words.size();
        System.out.printf("Total words: %d.\n", itemsCount);

        //Get list of only longest words
        int maxLength = words.get(0).length();
        List<String> longestWords = words.stream()
                .takeWhile(s-> s.length() == maxLength)
                .sorted()
                .collect(Collectors.toList());

        //Count percentage
        int maxOccurrence = longestWords.size();
        int percentage = getPercentage(maxOccurrence, itemsCount);

        //Print summary
        if(maxOccurrence == 1){
            System.out.printf("The longest word: %s (%d time(s), %d%s).\n", longestWords.get(0), maxOccurrence, percentage,"%");
        } else {
            System.out.printf("The longest words: %s (%d time(s), %d%s).\n",
                    String.join(", ", longestWords), maxOccurrence, percentage,"%");
        }
    }

    private static int getPercentage(int maxOccurrence, int size ){
        double percentage = (maxOccurrence / (double) size) * 100;
        return (int) percentage;
    }

}

