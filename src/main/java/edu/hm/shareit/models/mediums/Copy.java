package edu.hm.shareit.models.mediums;

public class Copy {
    private Medium medium;
    private String owner;

    public Copy(){
        this(null,null);
    }
    public Copy(Medium medium, String owner) {
        setMedium(medium);
        setOwner(owner);
    }

    public Medium getMedium() {
        return medium;
    }

    public String getOwner() {
        return owner;
    }

    public void setMedium(Medium medium){
        this.medium = medium;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }
}
