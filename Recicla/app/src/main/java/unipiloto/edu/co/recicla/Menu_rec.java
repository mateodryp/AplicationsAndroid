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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
    ArrayList<ListPublications> carton = new ArrayList<ListPublications>();
    ArrayList<ListPublications> vidrio = new ArrayList<ListPublications>();
    ArrayList<ListPublications> metal = new ArrayList<ListPublications>();
    ArrayList<ListPublications> papel = new ArrayList<ListPublications>();

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


        ImageView solicitudes_pendientes = (ImageView)  findViewById(R.id.MenuRec_solicitudes);
        solicitudes_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ListOwnRequestPen.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });
        ImageView historial = (ImageView)  findViewById(R.id.MenuRec_history);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ListHistoryRequest.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        ImageView config = (ImageView)  findViewById(R.id.MenuRec_config);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),changePasswordRecicler.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        Spinner filter = (Spinner) findViewById(R.id.spinner_filter);
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                String item_filter = filter.getSelectedItem().toString();
                switch (item_filter){
                    case "Carton":

                        for(int i = 0; i < lista.size(); i++){
                            if(lista.get(i).getType_material().equals("Carton")){
                                carton.add(lista.get(i));
                            }
                        }
                        ListView listViewCarton = (ListView) findViewById(R.id.MenuRec_list);
                        MyAdapterPublication adapterCarton = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,carton);
                        listViewCarton.setAdapter(adapterCarton);
                        listViewCarton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent1 = new Intent(getApplicationContext(), PublicationInfo.class);
                                intent1.putExtra("id_publication",String.valueOf(carton.get(i).getId_publication()));
                                intent1.putExtra("tipo_material",carton.get(i).getType_material());
                                intent1.putExtra("peso", carton.get(i).getWeight());
                                intent1.putExtra("volumen", carton.get(i).getVolume());
                                intent1.putExtra("direccion", carton.get(i).getAddress());
                                intent1.putExtra("descripcion", carton.get(i).getDescription());
                                intent1.putExtra("token", token);
                                intent1.putExtra("email", email);
                                intent1.putExtra("name", name);
                                intent1.putExtra("id", String.valueOf(id));
                                startActivity(intent1);
                                finish();
                            }
                        });
                        break;
                    case "Vidrio":
                        for(int i = 0; i < lista.size(); i++){
                            if(lista.get(i).getType_material().equals("Vidrio")){
                                vidrio.add(lista.get(i));
                            }
                        }
                        ListView listViewVidrio = (ListView) findViewById(R.id.MenuRec_list);
                        MyAdapterPublication adapterVidrio = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,vidrio);
                        listViewVidrio.setAdapter(adapterVidrio);
                        listViewVidrio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent1 = new Intent(getApplicationContext(), PublicationInfo.class);
                                intent1.putExtra("id_publication",String.valueOf(vidrio.get(i).getId_publication()));
                                intent1.putExtra("tipo_material",vidrio.get(i).getType_material());
                                intent1.putExtra("peso", vidrio.get(i).getWeight());
                                intent1.putExtra("volumen", vidrio.get(i).getVolume());
                                intent1.putExtra("direccion", vidrio.get(i).getAddress());
                                intent1.putExtra("descripcion", vidrio.get(i).getDescription());
                                intent1.putExtra("token", token);
                                intent1.putExtra("email", email);
                                intent1.putExtra("name", name);
                                intent1.putExtra("id", String.valueOf(id));
                                startActivity(intent1);
                                finish();
                            }
                        });
                        break;
                    case "Metal":
                        for(int i = 0; i < lista.size(); i++){
                            if(lista.get(i).getType_material().equals("Metal")){
                                metal.add(lista.get(i));
                            }
                        }
                        ListView listViewMetal = (ListView) findViewById(R.id.MenuRec_list);
                        MyAdapterPublication adapterMetal = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,metal);
                        listViewMetal.setAdapter(adapterMetal);
                        listViewMetal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent1 = new Intent(getApplicationContext(), PublicationInfo.class);
                                intent1.putExtra("id_publication",String.valueOf(metal.get(i).getId_publication()));
                                intent1.putExtra("tipo_material",metal.get(i).getType_material());
                                intent1.putExtra("peso", metal.get(i).getWeight());
                                intent1.putExtra("volumen", metal.get(i).getVolume());
                                intent1.putExtra("direccion", metal.get(i).getAddress());
                                intent1.putExtra("descripcion", metal.get(i).getDescription());
                                intent1.putExtra("token", token);
                                intent1.putExtra("email", email);
                                intent1.putExtra("name", name);
                                intent1.putExtra("id", String.valueOf(id));
                                startActivity(intent1);
                                finish();
                            }
                        });
                        break;
                    case "Papel":
                        for(int i = 0; i < lista.size(); i++){
                            if(lista.get(i).getType_material().equals("Papel")){
                                papel.add(lista.get(i));
                            }
                        }
                        ListView listViewPapel = (ListView) findViewById(R.id.MenuRec_list);
                        MyAdapterPublication adapterPapel = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,papel);
                        listViewPapel.setAdapter(adapterPapel);
                        listViewPapel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent1 = new Intent(getApplicationContext(), PublicationInfo.class);
                                intent1.putExtra("id_publication",String.valueOf(papel.get(i).getId_publication()));
                                intent1.putExtra("tipo_material",papel.get(i).getType_material());
                                intent1.putExtra("peso", papel.get(i).getWeight());
                                intent1.putExtra("volumen", papel.get(i).getVolume());
                                intent1.putExtra("direccion", papel.get(i).getAddress());
                                intent1.putExtra("descripcion", papel.get(i).getDescription());
                                intent1.putExtra("token", token);
                                intent1.putExtra("email", email);
                                intent1.putExtra("name", name);
                                intent1.putExtra("id", String.valueOf(id));
                                startActivity(intent1);
                                finish();
                            }
                        });
                        break;
                    default:
                        if(lista != null){
                            ListView listViewTodos = (ListView) findViewById(R.id.MenuRec_list);
                            MyAdapterPublication adapterTodos = new MyAdapterPublication(getApplicationContext(), R.layout.list_view_publications_rec,lista);
                            listViewTodos.setAdapter(adapterTodos);
                            listViewTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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