public class Filter {

    private final String type;
    private final String qualifier;
    private final String value;

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

}
