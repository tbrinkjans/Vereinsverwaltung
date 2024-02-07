package application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FinanceItem extends Item {

    private int value;
    private String source;
    private List<String> labels;

    public FinanceItem(UUID id, String name, Date date, int value, String source, List<String> labels) {
        super(id, name, date);
        this.value = value;
        this.source = source;
        this.labels = labels;
    }

    public FinanceItem(UUID id, String name, Date date, int value, String source) {
        this(id, name, date, value, source, new ArrayList<>());
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

    public List<String> getLabels() {
        return labels;
    }

}
