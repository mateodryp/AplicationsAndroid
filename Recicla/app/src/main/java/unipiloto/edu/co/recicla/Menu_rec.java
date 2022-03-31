package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.ListPublications;
import unipiloto.edu.co.recicla.models.LoginResponse;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class Menu_rec extends AppCompatActivity {

    private String token;
    private String email;
    private String name;
    private String id;
    private ArrayList<ListPublications> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_menu_rec);
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        TextView nameText = (TextView) findViewById(R.id.menuRec_name);
        nameText.setText(name);

        Call<List<ListPublications>> listResponseCall = RetrofitClient.getApiService().getListPublications();
        listResponseCall.enqueue(new Callback<List<ListPublications>>() {
            @Override
            public void onResponse(Call<List<ListPublications>> call, Response<List<ListPublications>> response) {
                lista = (ArrayList<ListPublications>) response.body();
                ListView listView = (ListView) findViewById(R.id.MenuRec_list);
                MyAdapterPublication adapter = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,lista);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListPublications>> call, Throwable t) {
                System.out.println("feo");
            }
        });


    }
}