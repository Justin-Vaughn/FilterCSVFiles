package dev.justin;

public class Filter {

    private final String type; // THEME
    private final String qualifier; // ==
    private final String value; // CHRISTMAS

    public Filter(String type, String qualifier, String value) {
        this.type = type;
        this.qualifier = qualifier;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Type: " + getType() + " Qualifier: " + getQualifier() + " Value: " + getValue();
    }
}
