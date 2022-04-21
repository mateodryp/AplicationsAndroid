package unipiloto.edu.co.recicla.models;

public class ListOwnPublications {

    private int id_publication;
    private String type_material;
    private String address;
    private String weight;
    private String volume;
    private String description;
    private String timestamp;
    private boolean state;
    private int user;

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public String getType_material() {
        return type_material;
    }

    public void setType_material(String type_material) {
        this.type_material = type_material;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
