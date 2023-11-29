import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // read the CSV file
            List<Row> rows = readCSVFile("LegoSets.csv");
            System.out.println(rows);

            // take the filtering instructions


            // filter the CSV file

            // write the new CSV
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

    public List<Filter> filterInstructions() {
        Scanner scanner = new Scanner(System.in);

        // ask user for filter instructions
        // show the list of filters available
        // keep going until they run out of filter options or they "q"
    }

}