package application.model;

import java.util.Date;
import java.util.UUID;

public class FinanceItem extends Item {

    private String type;
    private String[] labels;
    private int value;
    private String source;

    public FinanceItem(UUID id, String name, Date date, String type, String[] labels, int value, String source) {
        super(id, name, date);
        this.type = type;
        this.labels = labels;
        this.value = value;
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

}
