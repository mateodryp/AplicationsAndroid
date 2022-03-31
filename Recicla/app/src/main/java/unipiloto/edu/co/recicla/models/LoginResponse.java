package unipiloto.edu.co.recicla.models;

public class LoginResponse {
    private String token;
    private String name;
    private String last_name;
    private String email;
    private boolean IsRecycler;
    private boolean IsProvider;
    private String message;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRecycler() {
        return IsRecycler;
    }

    public void setRecycler(boolean recycler) {
        IsRecycler = recycler;
    }

    public boolean isProvider() {
        return IsProvider;
    }

    public void setProvider(boolean provider) {
        IsProvider = provider;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
