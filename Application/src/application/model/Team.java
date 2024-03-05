package application.model;

import java.util.UUID;

import application.common.BaseEntity;

public class Team extends BaseEntity {

    private String name;
    private String activity;

    public Team(UUID id, String name, String activity) {
        super(id);
        this.name = name;
        this.activity = activity;
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

}
