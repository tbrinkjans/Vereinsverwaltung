package application.model;

import application.common.BaseEntity;
import java.util.Date;
import java.util.UUID;

public class Item extends BaseEntity {

    private String name;
    private Date date;

    public Item(UUID id, String name, Date date) {
        super(id);
        this.name = name;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
