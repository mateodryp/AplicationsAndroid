package unipiloto.edu.co.recicla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    @SerializedName("error")
    @Expose
    String error;

    @SerializedName("successful")
    @Expose
    String successful;
}
