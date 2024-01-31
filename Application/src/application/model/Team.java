package application.model;

import application.common.BaseEntity;
import java.util.Date;

public class Team extends BaseEntity {
    
    private String name;
    private String activity;
    private Date date;

    public Team(String id, String name, String activity, Date date) {
        super(id);
        this.name = name;
        this.activity = activity;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
    
    public String getActivity() {
        return activity;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }
    
}