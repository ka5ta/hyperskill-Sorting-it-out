package sorting;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static sorting.DataTypeStatsGenerator.getStatisticsByType;


public class Main {

    private static SortingType sortingType;
    private static String dataType = "word";
    private static File inputFile;
    private static File outputFile;


    public static void main(final String[] args) throws IOException {
        getCommandFromArgs(args);
        String dataIn;
        if(Objects.isNull(inputFile)){
            //Scanner input
            dataIn = getDataFromUserInput();
        } else {
            //File input
            dataIn = getDataFromFile(inputFile);
        }

        String dataOut = getStatisticsByType(dataIn, dataType, sortingType);

        //Save to file only if name is provided.
        if (Objects.nonNull(outputFile)) {
            saveStatisticsToFile(dataOut, outputFile);
        } else {
            System.out.println(dataOut);
        }
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
                    case "-inputFile":
                        //Path path = Paths.get(File.separator, "Users", "kurbanska", "IdeaProjects", "Sorting Tool", args[i+1]);
                        //inputFile = path.toFile();
                        inputFile = new File(args[i+1]);
                        break;
                    case "-outputFile":
                        outputFile = new File(args[i+1]);
                        break;
                    default:
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped.", args[i]);
                }
            }
        }
    }

    public static String getDataFromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value;
            while( (value = reader.read() ) != -1){
            sb.append( (char) value);
            }
            //System.out.println(sb);
            return sb.toString();
        } catch (IOException e) {
            System.out.printf("File: \"%s\" doesn't exists in work directory.\n", file);
        }
        throw new FileNotFoundException();
    }

    //Used only if there is no file to read from
    public static String getDataFromUserInput(){
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            sb.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return sb.toString();
    }


    private static void saveStatisticsToFile(String dataOut, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(dataOut);
        } catch (IOException e) {
            System.out.printf("File \"%s\" could not be created.\n", file);
            throw new IOException();
        }
    }
}

