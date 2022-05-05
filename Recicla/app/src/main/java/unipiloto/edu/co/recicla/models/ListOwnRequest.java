package unipiloto.edu.co.recicla.models;

public class ListOwnRequest {
    private int id_publication;
    private String type_material;
    private String address;
    private double weight;
    private double volume;
    private String description;
    private String timestamp;
    private boolean state;
    private int user;
    private String recycler;
    private int id_request;
    private int id_recycler;

    public int getId_recycler() {
        return id_recycler;
    }

    public void setId_recycler(int id_recycler) {
        this.id_recycler = id_recycler;
    }

    private String state_request;

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
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

    public String getRecycler() {
        return recycler;
    }

    public void setRecycler(String recycler) {
        this.recycler = recycler;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public String getState_request() {
        return state_request;
    }

    public void setState_request(String state_request) {
        this.state_request = state_request;
    }
}
