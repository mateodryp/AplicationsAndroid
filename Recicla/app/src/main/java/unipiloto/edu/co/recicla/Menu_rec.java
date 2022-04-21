package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
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
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent1 = new Intent(getApplicationContext(), PublicationInfo.class);
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
            public void onFailure(Call<List<ListPublications>> call, Throwable t) {
                System.out.println("feo");
            }
        });


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