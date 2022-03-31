package unipiloto.edu.co.recicla.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import unipiloto.edu.co.recicla.models.ListPublications;
import unipiloto.edu.co.recicla.models.LoginRequest;
import unipiloto.edu.co.recicla.models.LoginResponse;
import unipiloto.edu.co.recicla.models.PublicacionRequest;
import unipiloto.edu.co.recicla.models.Response;

public interface APIService {

    @FormUrlEncoded
    @POST("users/recycler/")
    Call<Response>registerRecUser(
            @Field("name") String first_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("phone_number") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/provider/")
    Call<Response>registerProvUser(
            @Field("name") String name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("address") String address,
            @Field("password") String password
    );

   /* @FormUrlEncoded
    @POST("users/login/")
   Call<Response>login(
        @Field("email") String email,
        @Field("password") String password
    );*/

    @POST("users/login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @GET("publications/list_publication/")
    Call<List<ListPublications>> getListPublications();


    @FormUrlEncoded
    @POST("publications/publication/")
    Call<PublicacionRequest>registerPublication(
            @Field("type_material") String type_material,
            @Field("address") String address,
            @Field("weight") String weight,
            @Field("volume") String volume,
            @Field("description") String description,
            @Field("user") int user
    );




}
