package api_Payloads.Pet;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private int id;
    private String name;
    private Pet_Category category;
    private List<String> photoUrls = new ArrayList<>();
    private List<Pet_Tags> tags = new ArrayList<>();
    private String status;

    public Pet(int id, String name, Pet_Category category, List<String> photoUrls, List<Pet_Tags> tags, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet() {
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

    public Pet_Category getCategory() {
        return category;
    }

    public void setCategory(Pet_Category category) {
        this.category = category;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Pet_Tags> getTags() {
        return tags;
    }

    public void setTags(List<Pet_Tags> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
