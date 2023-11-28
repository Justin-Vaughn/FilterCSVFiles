import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, String> rowInfo = new HashMap<>();

    public Row(String[] header, String[] currentRow) {
        for (int i = 0; i < header.length; i++) {
            rowInfo.put(header[i], currentRow[i]);
        }
    }
}
