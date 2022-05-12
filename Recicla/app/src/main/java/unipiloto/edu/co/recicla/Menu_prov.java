package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.ListOwnPublications;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class Menu_prov extends AppCompatActivity {
    private String token;
    private String email;
    private String name;
    private String id;
    private ArrayList<ListOwnPublications> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prov);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        TextView nameText = (TextView) findViewById(R.id.menuProv_name);
        nameText.setText(name);

        ImageView solicitudes = (ImageView) findViewById(R.id.menuProv_solicitudes);
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

        ImageView history = (ImageView) findViewById(R.id.menuProv_history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ListHistoryPublication.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        Call<List<ListOwnPublications>> listCall = RetrofitClient.getApiService().getListOwnPublications(Integer.parseInt(id));
        listCall.enqueue(new Callback<List<ListOwnPublications>>() {
            @Override
            public void onResponse(Call<List<ListOwnPublications>> call, Response<List<ListOwnPublications>> response) {
                lista = (ArrayList<ListOwnPublications>) response.body();
                ListView listView = (ListView) findViewById(R.id.MenuProv_list);
                MyAdapterOwnPublication adapter = new MyAdapterOwnPublication(getApplicationContext(), R.layout.list_view_publications_prov, lista);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent1 = new Intent(getApplicationContext(), PublicationsActions.class);
                        intent1.putExtra("id_publication",String.valueOf(lista.get(i).getId_publication()));
                        intent1.putExtra("tipo_material",lista.get(i).getType_material());
                        intent1.putExtra("peso", lista.get(i).getWeight());
                        intent1.putExtra("volumen", lista.get(i).getVolume());
                        intent1.putExtra("direccion", lista.get(i).getAddress());
                        intent1.putExtra("descripcion", lista.get(i).getDescription());
                        intent1.putExtra("token", token);
                        intent1.putExtra("email", email);
                        intent1.putExtra("name", name);
                        intent1.putExtra("id", String.valueOf(id));
                        startActivity(intent1);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ListOwnPublications>> call, Throwable t) {

            }
        });
    }


    public void onClickPublicar(View view){
        Intent intent=new Intent(getApplicationContext(),PublicarMaterial.class);
        intent.putExtra("token", token);
        intent.putExtra("email", email);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent intent=new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}