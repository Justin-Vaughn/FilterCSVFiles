package dev.justin;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, String> rowInfo = new HashMap<>();

    public Row(String[] header, String[] currentRow) {
        for (int i = 0; i < header.length; i++) {
            // maps header to col
            rowInfo.put(header[i].toLowerCase(), currentRow[i].trim());
        }
    }

    public String get(String key) {
        return rowInfo.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String key : rowInfo.keySet()) {
            sb.append(key).append(": ").append(rowInfo.get(key)).append(" ");
        }
        return sb.append("\n").toString();
    }
}
