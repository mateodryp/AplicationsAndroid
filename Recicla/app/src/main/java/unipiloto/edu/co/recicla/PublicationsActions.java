package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.DeleteResponse;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class PublicationsActions extends AppCompatActivity {

    private String id_publication;
    private String tipo_material;
    private String peso;
    private String volumen;
    private String direccion;
    private String description;
    private String token;
    private String email;
    private String name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_actions);
        Intent intent = getIntent();
        id_publication = intent.getStringExtra("id_publication");
        tipo_material = intent.getStringExtra("tipo_material");
        peso = intent.getStringExtra("peso");
        volumen = intent.getStringExtra("volumen");
        direccion = intent.getStringExtra("direccion");
        description = intent.getStringExtra("descripcion");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        TextView tipo_material_view = (TextView) findViewById(R.id.PubliAct_public_typeMaterial);
        TextView peso_view = (TextView) findViewById(R.id.PubliAct_public_peso);
        TextView volumen_view = (TextView) findViewById(R.id.PubliAct_public_volumen);
        TextView direccion_view = (TextView) findViewById(R.id.PubliAct_public_direccion);
        TextView descripcion_view = (TextView) findViewById(R.id.PubliAct_public_description);
        ImageView imagen_material = (ImageView) findViewById(R.id.PubliAct_public_image);
        Button delete = (Button) findViewById(R.id.PubliAct_public_Delete);
        Button edit = (Button) findViewById(R.id.PubliAct_public_Edit);

        tipo_material_view.setText("Tipo de material: " + tipo_material);
        peso_view.setText("Peso: " + peso + " Kg");
        volumen_view.setText("Volumen" + volumen + "m3");
        direccion_view.setText("Dirección: " + direccion);
        descripcion_view.setText("Descripción: " + description);

        switch (tipo_material){
            case "Vidrio":
                imagen_material.setImageResource(R.drawable.vidrio);
                break;

            case "Carton":
                imagen_material.setImageResource(R.drawable.carton);
                break;

            case "Metal":
                imagen_material.setImageResource(R.drawable.metal);
                break;

            case "Papel":
                imagen_material.setImageResource(R.drawable.papel);
                break;
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePublication((view));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPublication((view));
            }
        });

    }

    public void deletePublication(View view){
        APIService service = RetrofitClient.getApiService();
        Call<DeleteResponse> call = service.deletePublication(Integer.parseInt(id_publication));
        call.enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                String code= String.valueOf(response.raw().code());
                if(code.equals("200")){
                    Toast.makeText(getApplicationContext(),"Publicación eliminada",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Menu_prov.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al eliminar la publicación",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),SolicitudesActivasProv.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });
    }

    public void editPublication(View view){
        Intent intent=new Intent(getApplicationContext(),EditPublication.class);
        intent.putExtra("id_publication", id_publication);
        intent.putExtra("tipo_material", tipo_material);
        intent.putExtra("peso", peso);
        intent.putExtra("volumen", volumen);
        intent.putExtra("direccion", direccion);
        intent.putExtra("descripcion", description);
        intent.putExtra("token", token);
        intent.putExtra("email", email);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();

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