package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.EditRequest;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class EditPublication extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit_publication);
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

        Spinner material = (Spinner) findViewById(R.id.EditPublication_materials);
        EditText peso_edit = (EditText) findViewById(R.id.EditPublication_peso);
        EditText volumen_edit = (EditText) findViewById(R.id.EditPublication_volumen);
        EditText direccion_edit = (EditText) findViewById(R.id.EditPublication_direccion);
        EditText descripcion_edit = (EditText) findViewById(R.id.EditPublication_descripcion);
        Button edit = (Button) findViewById(R.id.EditPublication_button);

        peso_edit.setText(peso);
        volumen_edit.setText(volumen);
        direccion_edit.setText(direccion);
        descripcion_edit.setText(description);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPublication(view);
            }
        });
    }

    public void editPublication(View view){
        Spinner material = (Spinner) findViewById(R.id.EditPublication_materials);
        EditText peso_edit = (EditText) findViewById(R.id.EditPublication_peso);
        EditText volumen_edit = (EditText) findViewById(R.id.EditPublication_volumen);
        EditText direccion_edit = (EditText) findViewById(R.id.EditPublication_direccion);
        EditText descripcion_edit = (EditText) findViewById(R.id.EditPublication_descripcion);
        Button edit = (Button) findViewById(R.id.EditPublication_button);

        APIService service = RetrofitClient.getApiService();
        Call<EditRequest> call = service.editPublication(
                Integer.parseInt(id_publication),
                material.getSelectedItem().toString(),
                direccion_edit.getText().toString(),
                peso_edit.getText().toString(),
                volumen_edit.getText().toString(),
                descripcion_edit.getText().toString(),
                Integer.parseInt(id)
        );

        call.enqueue(new Callback<EditRequest>() {
            @Override
            public void onResponse(Call<EditRequest> call, Response<EditRequest> response) {
                String code= String.valueOf(response.raw().code());
                if(code.equals("201")){
                    Toast.makeText(getApplicationContext(),"Publicación modificada",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Menu_prov.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al modificar la solicitud",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditRequest> call, Throwable t) {

            }
        });

    }
}