package ro.pub.cs.systems.eim.practicaltest02;

public class DataModel {

    private String definition;

    public String getDefinition() {
        return definition;
    }

    public DataModel(String definition) {
        this.definition = definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "definition='" + definition + '\'' +
                '}';
    }
}
