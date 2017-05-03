package edu.hm.shareit.models.mediums;

public class Medium {
    private String title;

    public Medium(){
        this(null);
    }

    public Medium(String title) {
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Medium{" +
                "title='" + title + "\'" +
                "}";
    }
}
