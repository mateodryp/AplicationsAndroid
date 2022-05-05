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
import unipiloto.edu.co.recicla.models.AcceptResponse;
import unipiloto.edu.co.recicla.models.ActionRequestPublication;
import unipiloto.edu.co.recicla.models.RejectResponse;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class RequestInfo extends AppCompatActivity {

    private String id_publication;
    private String id_request;
    private String tipo_material;
    private String recycler;
    private String date;
    private String description;
    private String token;
    private String email;
    private String name;
    private String id;
    private String id_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);
        Intent intent = getIntent();
        id_publication = intent.getStringExtra("id_publication");
        id_request = intent.getStringExtra("id_request");
        tipo_material = intent.getStringExtra("tipo_material");
        recycler = intent.getStringExtra("recycler");
        date = intent.getStringExtra("date");
        description = intent.getStringExtra("description");
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        id_recycler = intent.getStringExtra("id_recycler");

        TextView idPublication = (TextView) findViewById(R.id.RequestInfo_public_idPublication) ;
        TextView typeMaterial = (TextView) findViewById(R.id.RequestInfo_public_typeMaterial);
        TextView recyclerName = (TextView) findViewById(R.id.RequestInfo_public_recicler);
        TextView dateRequest = (TextView) findViewById(R.id.RequestInfo_public_date);
        TextView descriptionRequest = (TextView) findViewById(R.id.RequestInfo_public_description);
        ImageView imagen_material = (ImageView) findViewById(R.id.RequestInfo_public_image);
        Button accept = (Button) findViewById(R.id.RequestInfo_public_Accept);
        Button reject = (Button) findViewById(R.id.RequestInfo_public_Reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aceptarSolicitud(view);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechazarSolicitud(view);
            }
        });

        typeMaterial.setText("Tipo de material: " +tipo_material);
        recyclerName.setText("Solicitante: " +recycler);
        idPublication.setText("ID Publicación: " +id_publication);
        dateRequest.setText("Fecha: " +date.substring(0,10));
        descriptionRequest.setText("Descripción: " +description);

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




    }

    public void rechazarSolicitud(View view){
        APIService service = RetrofitClient.getApiService();
        Call<ActionRequestPublication> call= service.rejectRequest(Integer.parseInt(id_publication), Integer.parseInt(id_recycler), Integer.parseInt(id_request));
        call.enqueue(new Callback<ActionRequestPublication>() {
            @Override
            public void onResponse(Call<ActionRequestPublication> call, Response<ActionRequestPublication> response) {
                String code= String.valueOf(response.raw().code());
                if(code.equals("200")){
                    Toast.makeText(getApplicationContext(),"Solicitud rechazada",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),SolicitudesActivasProv.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al rechazar la solicitud",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ActionRequestPublication> call, Throwable t) {
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

    public void aceptarSolicitud(View view){
        APIService service = RetrofitClient.getApiService();
        Call<ActionRequestPublication> call= service.acceptRequest(Integer.parseInt(id_publication), Integer.parseInt(id_recycler), Integer.parseInt(id_request));
        call.enqueue(new Callback<ActionRequestPublication>() {
            @Override
            public void onResponse(Call<ActionRequestPublication> call, Response<ActionRequestPublication> response) {
                String code= String.valueOf(response.raw().code());
                if(code.equals("200")){
                    Toast.makeText(getApplicationContext(),"Solicitud aceptada",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),SolicitudesActivasProv.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al aceptar la solicitud",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ActionRequestPublication> call, Throwable t) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            Intent intent=new Intent(getApplicationContext(), SolicitudesActivasProv.class);
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