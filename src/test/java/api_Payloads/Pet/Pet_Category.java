package api_Payloads.Pet;

public class Pet_Category {
    private int id;
    private String name;


    public Pet_Category(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Pet_Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Pet_Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
