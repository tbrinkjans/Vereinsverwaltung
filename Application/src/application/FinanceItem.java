/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

public class FinanceItem extends Item {
    private String type;
    private String[] labels;
    private int value;
    private String source;

    public String getType() {
        return type;
    }

    public String[] getLabels() {
        return labels;
    }

    public int getValue() {
        return value;
    }

    public String getSource() {
        return source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
