/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

public class InventoryItem extends Item {
    private String activity;
    private Location location;

    public String getActivity() {
        return activity;
    }

    public Location getLocation() {
        return location;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
}
