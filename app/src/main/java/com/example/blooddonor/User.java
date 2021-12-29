package com.example.blooddonor;

public class User {
    private String Name;
    private String Location;
    private String BloodGroup;
    private String status;

    public User(String Name,String Location,String status,String BloodGroup){
        this.BloodGroup = BloodGroup;
        this.status = status;
        this.Location = Location;
        this.Name = Name;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
