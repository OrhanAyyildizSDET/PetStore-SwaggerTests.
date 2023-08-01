package api_Payloads.Pet;

import java.util.Arrays;

public class Pet_Tags {
   private long id;
   private String name;

    public Pet_Tags(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pet_Tags() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet_Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
