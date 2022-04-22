package sorting;

public class Main {

    private static SortingType sortingType;
    private static String dataType;


    public static void main(final String[] args) {
        getCommandFromArgs(args);
        getStatistics(dataType);
    }


    private static void getCommandFromArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("-")) {
                switch (args[i]) {
                    case "-sortingType":
                        try {
                            sortingType = SortingType.getSortingType(args[i + 1]);
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("No sorting type defined!");
                            break;
                        }
                    case "-dataType":
                        try {
                            dataType = args[i + 1];
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("No data type defined!");
                            break;
                        }
                    default:
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped.", args[i]);
                }
            }
        }
    }

    private static void getStatistics(String dataType) {
        if (dataType == null) {
            return;
        }
        switch (dataType) {
            case "long":
                new LongDataType().printStats(sortingType);
                break;
            case "line":
                new LineDataType().printStats(sortingType);
                break;
            case "word":
                new WordDataType().printStats(sortingType);
                break;
            default:
                System.out.println("No data type defined!");
                break;
        }
    }
}

