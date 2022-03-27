package unipiloto.edu.co.recicla.models;

public class UserRec {
    public String getName() {
        return fist_name;
    }

    public void setName(String name) {
        this.fist_name = name;
    }

    public String getLastname() {
        return last_name;
    }

    public void setLastname(String lastname) {
        this.last_name = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String fist_name;
    String last_name;
    String email;
    String phone;
    String password;
}
