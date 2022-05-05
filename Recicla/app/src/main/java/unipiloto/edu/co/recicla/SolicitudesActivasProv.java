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
import unipiloto.edu.co.recicla.models.ListOwnRequest;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class SolicitudesActivasProv extends AppCompatActivity {
    private String token;
    private String email;
    private String name;
    private String id;
    private ArrayList<ListOwnRequest> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes_activas_prov);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        TextView nameText = (TextView) findViewById(R.id.menuProvSA_name);
        nameText.setText(name);

        ImageView home = (ImageView) findViewById(R.id.menuProvSA_home);
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

        Call<List<ListOwnRequest>> call = RetrofitClient.getApiService().getListOwnRequest(Integer.parseInt(id));
        call.enqueue(new Callback<List<ListOwnRequest>>() {
            @Override
            public void onResponse(Call<List<ListOwnRequest>> call, Response<List<ListOwnRequest>> response) {
                lista = (ArrayList<ListOwnRequest>) response.body();
                ListView listView = (ListView) findViewById(R.id.MenuProvSA_list);
                MyAdapterOwnRequestAv adapter = new MyAdapterOwnRequestAv(getApplicationContext(), R.layout.list_view_solicitudes_proveedor, lista);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent1 = new Intent(getApplicationContext(), RequestInfo.class);
                        intent1.putExtra("tipo_material", lista.get(i).getType_material());
                        intent1.putExtra("recycler", lista.get(i).getRecycler());
                        intent1.putExtra("id_publication", String.valueOf(lista.get(i).getId_publication()));
                        intent1.putExtra("date", lista.get(i).getTimestamp());
                        intent1.putExtra("description", lista.get(i).getDescription());
                        intent1.putExtra("token", token);
                        intent1.putExtra("email", email);
                        intent1.putExtra("name", name);
                        intent1.putExtra("id_request", String.valueOf(lista.get(i).getId_request()));
                        intent1.putExtra("id_recycler", String.valueOf(lista.get(i).getId_recycler()));
                        intent1.putExtra("id", String.valueOf(id));
                        startActivity(intent1);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ListOwnRequest>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent intent=new Intent(getApplicationContext(), Menu_prov.class);
            intent.putExtra("token", token);
            intent.putExtra("email", email);
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}