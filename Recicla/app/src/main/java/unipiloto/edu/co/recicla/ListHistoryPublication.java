package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.ListOwnPublications;
import unipiloto.edu.co.recicla.models.ListOwnRequest;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class ListHistoryPublication extends AppCompatActivity {
    private String token;
    private String email;
    private String name;
    private String id;
    private ArrayList<ListOwnPublications> publications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history_publication);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        ImageView home = (ImageView) findViewById(R.id.Prov_Pub_His_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Menu_prov.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        ImageView solicitudes = (ImageView) findViewById(R.id.Prov_Pub_His_solicitudes);
        solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SolicitudesActivasProv.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        Call<List<ListOwnPublications>> call = RetrofitClient.getApiService().getHistoryPublication(Integer.parseInt(id));
        call.enqueue(new Callback<List<ListOwnPublications>>() {
            @Override
            public void onResponse(Call<List<ListOwnPublications>> call, Response<List<ListOwnPublications>> response) {
                publications = (ArrayList<ListOwnPublications>) response.body();
                ListView listView = (ListView) findViewById(R.id.Prov_Pub_His_list);
                MyAdapterOwnPublicationHistory adapter = new MyAdapterOwnPublicationHistory(getApplicationContext(), R.layout.activity_list_history_publication, publications);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListOwnPublications>> call, Throwable t) {

            }
        });
    }
}