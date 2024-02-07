package application.model;

import java.util.Date;
import java.util.UUID;

public class InventoryItem extends Item {

    private String activity;
    private Location location;

    public InventoryItem(UUID id, String name, Date date, String activity, Location location) {
        super(id, name, date);
        this.activity = activity;
        this.location = location;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
