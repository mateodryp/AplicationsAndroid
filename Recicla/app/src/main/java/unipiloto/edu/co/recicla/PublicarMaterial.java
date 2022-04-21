package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.ListOwnPublications;
import unipiloto.edu.co.recicla.models.ListPublications;
import unipiloto.edu.co.recicla.models.PublicacionRequest;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class PublicarMaterial extends AppCompatActivity {
    private String token;
    private String email;
    private String name;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_material);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        Button boton = (Button) findViewById(R.id.publicarMaterial_button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicarMaterial(view);
            }
        });

        ImageView home = (ImageView) findViewById(R.id.menuProv_home);
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
    }

    public void publicarMaterial(View view){
        Spinner material = (Spinner) findViewById(R.id.publicarMaterial_materials);
        EditText peso = (EditText) findViewById(R.id.publicarMaterial_peso);
        EditText volumen = (EditText) findViewById(R.id.publicarMaterial_volumen);
        EditText direccion = (EditText) findViewById(R.id.publicarMaterial_direccion);
        EditText descripcion = (EditText) findViewById(R.id.publicarMaterial_descripcion);
        APIService service = RetrofitClient.getApiService();

        if(!material.getSelectedItem().toString().equals("") && !peso.getText().toString().equals("") && !volumen.getText().toString().equals("") && !direccion.getText().toString().equals("") && !descripcion.getText().toString().equals("")){
            int a = Integer.parseInt(id);
            Call<PublicacionRequest> call = service.registerPublication(
                    material.getSelectedItem().toString()
                    ,direccion.getText().toString()
                    ,peso.getText().toString()
                    ,volumen .getText().toString()
                    ,descripcion.getText().toString()
                    ,Integer.parseInt(id)
            );
            call.enqueue(new Callback<PublicacionRequest>() {
                @Override
                public void onResponse(Call<PublicacionRequest> call, Response<PublicacionRequest> response) {
                    String code= String.valueOf(response.raw().code());
                    if(code.equals("201")){
                        Toast.makeText(getApplicationContext(),"Publicación registrada",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),Menu_prov.class);
                        intent.putExtra("token", token);
                        intent.putExtra("email", email);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error al publicar la solicitud",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<PublicacionRequest> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Menu_prov.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }
            });

        }else{
            Toast.makeText(getApplicationContext(),"Por favor llenar todos los campos",Toast.LENGTH_LONG).show();
        }

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