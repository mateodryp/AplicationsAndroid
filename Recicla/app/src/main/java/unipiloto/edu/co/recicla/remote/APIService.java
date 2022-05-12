package unipiloto.edu.co.recicla.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import unipiloto.edu.co.recicla.models.ActionRequestPublication;
import unipiloto.edu.co.recicla.models.DeleteResponse;
import unipiloto.edu.co.recicla.models.EditRequest;
import unipiloto.edu.co.recicla.models.ListOwnPublications;
import unipiloto.edu.co.recicla.models.ListOwnRequest;
import unipiloto.edu.co.recicla.models.ListPublications;
import unipiloto.edu.co.recicla.models.LoginRequest;
import unipiloto.edu.co.recicla.models.LoginResponse;
import unipiloto.edu.co.recicla.models.PublicacionRequest;
import unipiloto.edu.co.recicla.models.Response;
import unipiloto.edu.co.recicla.models.SolicitudRequest;

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

    @GET("publications/publications_availables_all/")
    Call<List<ListPublications>> getListPublications();

    @GET("publications/publications_availables/{id}")
    Call<List<ListOwnPublications>> getListOwnPublications(@Path("id") int id);

    @GET("publications/publications_all/{id}/")
    Call<List<ListOwnPublications>> getHistoryPublication(@Path("id") int id);

    @FormUrlEncoded
    @POST("http://168.138.144.152:8000/requests/accepted_request/")
    Call<ActionRequestPublication> acceptRequest(
            @Field("publication") int publication,
            @Field("recycler") int recycler,
            @Field("id_request") int id_request
    );

    @FormUrlEncoded
    @POST("http://168.138.144.152:8000/requests/reject_request/")
    Call<ActionRequestPublication> rejectRequest(
            @Field("publication") int publication,
            @Field("recycler") int recycler,
            @Field("id_request") int id_request
    );

    @DELETE("http://168.138.144.152:8000/publications/publication_delete/{id}/")
    Call<DeleteResponse> deletePublication(@Path("id") int id);

    @GET("requests/requests_availables/{id}")
    Call<List<ListOwnRequest>> getListOwnRequest(@Path("id") int id);

    @GET("requests/requests_availables_recycler/{id}/")
    Call<List<ListOwnRequest>> getListOwnRequestPen(@Path("id") int id);

    @GET("requests/requests_all_recycler/{id}/")
    Call<List<ListOwnRequest>> getHistoryRequest(@Path("id") int id);

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

    @FormUrlEncoded
    @PUT("publications/publication_update/{id}/")
    Call<EditRequest>editPublication(
            @Path("id") int id,
            @Field("type_material") String type_material,
            @Field("address") String address,
            @Field("weight") String weight,
            @Field("volume") String volume,
            @Field("description") String description,
            @Field("user") int user
    );

    @FormUrlEncoded
    @POST("requests/add_request/")
    Call<SolicitudRequest>registerSolicitud(
            @Field("recycler") int recycler,
            @Field("publication") int publication
    );




}
