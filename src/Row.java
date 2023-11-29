import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, String> rowInfo = new HashMap<>();

    public Row(String[] header, String[] currentRow) {
        for (int i = 0; i < header.length; i++) {
            rowInfo.put(header[i], currentRow[i]);
        }
    }

    public String get(String key) {
        return rowInfo.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String key : rowInfo.keySet()) {
            sb.append("<").append(key).append(", ").append(rowInfo.get(key)).append(">\n");
        }
        return sb.toString();
    }
}
