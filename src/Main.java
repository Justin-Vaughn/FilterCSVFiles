import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String[] CSVHeader;

    public static void main(String[] args) {
        try {
            // read the CSV file
            List<Row> rows = readCSVFile("LegoSets.csv");

            // take the filtering instructions
            List<Filter> filter = filterInstructions();

            // filter the CSV file
            List<Row> newRows = filterCSV(rows, filter);

            // write the new CSV
            writeCSVFile("output.csv", newRows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Row> readCSVFile(String path) throws FileNotFoundException {
        List<Row> rows = new ArrayList<>();
        String[] firstLine = new String[]{};

        Scanner scanner = new Scanner(new File(path));
        boolean isFirstLine = true;

        while (scanner.hasNext()) {

            String[] row = scanner.nextLine().split(",");

            if (isFirstLine) {
                firstLine = row;
                CSVHeader = row;
                isFirstLine = false;
                continue;
            }

            // skips the line if it is incorrectly formatted in the CSV file
            if (row.length != firstLine.length) continue;

            // creates a Row object from the current row
            Row currentRow = new Row(firstLine, row);

            rows.add(currentRow);
        }
        return rows;
    }

    public static List<Filter> filterInstructions() {
        Scanner scanner = new Scanner(System.in);
        List<Filter> filter = new ArrayList<>();

        System.out.print("Filter Instructions [");
        for (int i = 0; i < CSVHeader.length; i++) {
            System.out.print(i != CSVHeader.length - 1 ? CSVHeader[i] + ", " : CSVHeader[i]
                    + "]\n(Example: 'parts > 2000,year < 2015'): ");
        }

        String[] userInput = scanner.nextLine().split(",");

        // loops to turn the user input array into a List of Filter objects
        for (String userFilters : userInput) {
            String[] currentFilterArray = userFilters.split(" ");
            Filter currentFilter = new Filter(currentFilterArray[0], currentFilterArray[1], currentFilterArray[2]);
            filter.add(currentFilter);
        }

        return filter;
    }

    public static List<Row> filterCSV(List<Row> rows, List<Filter> filters) {
        // base case filters are <= 0
        if (filters.isEmpty()) return rows;

        // selects and removes the first filter and creates a new list for the new rows that pass the filter
        Filter currentFilter = filters.removeLast();
        List<Row> filteredRows = new ArrayList<>();

        // test all the rows to see if they meet the filter requirements
        for (Row row : rows) {
            String rowVal = row.get(currentFilter.getType().toLowerCase());
            String filterVal = currentFilter.getValue();

            if (comparison(rowVal, filterVal, currentFilter.getQualifier())) filteredRows.add(row);
        }

        // return the new rows list with filters-1 (from the one that was used)
        return filterCSV(filteredRows, filters);
    }

    private static boolean comparison(String s1, String s2, String operator) {
        try {
            return switch (operator) {
                case "==" -> isFloat(s1) && isFloat(s2)
                        ? Float.parseFloat(s1) == Float.parseFloat(s2)
                        : s1.equalsIgnoreCase(s2);
                case "!=" -> isFloat(s1) && isFloat(s2)
                        ? Float.parseFloat(s1) != Float.parseFloat(s2)
                        : !(s1.equalsIgnoreCase(s2));
                case "<" -> Float.parseFloat(s1) < Float.parseFloat(s2);
                case ">" -> Float.parseFloat(s1) > Float.parseFloat(s2);
                case "<=" -> Float.parseFloat(s1) <= Float.parseFloat(s2);
                case ">=" -> Float.parseFloat(s1) >= Float.parseFloat(s2);
                default -> throw new IllegalArgumentException("Invalid Operator: " + operator);
            };
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid filter options with operator: " + s1 + " " + operator + " " + s2);
        }
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void writeCSVFile(String path, List<Row> rows) throws IOException {
        FileWriter file = new FileWriter(path, false);

        // write CSV header
        for (int i = 0; i < CSVHeader.length; i++) {
            file.write(i != CSVHeader.length - 1 ? CSVHeader[i] + ", " : CSVHeader[i]);
        }

        // write rows
        for (Row row : rows) {
            for (int j = 0; j < CSVHeader.length; j++) {

                file.write( j != CSVHeader.length - 1 ? row.get(CSVHeader[j]) + "," : row.get(CSVHeader[j]) + "\n");

            }
        }

        file.close();
    }
}