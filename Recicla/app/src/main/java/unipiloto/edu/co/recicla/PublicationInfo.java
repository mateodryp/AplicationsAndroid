package unipiloto.edu.co.recicla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;
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
import unipiloto.edu.co.recicla.models.SolicitudRequest;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;
import android.util.Log;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class PublicationInfo extends AppCompatActivity  {
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
    private double latitud;
    private double longitud;
    FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_info);
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(PublicationInfo.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
        else {
            ActivityCompat.requestPermissions(PublicationInfo.this
                    , new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(PublicationInfo.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    System.out.println(location);
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(PublicationInfo.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            latitud= (double) addresses.get(0).getLatitude();
                            longitud = (double) addresses.get(0).getLongitude();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }
            });
        }

    TextView tipo_material_view = (TextView) findViewById(R.id.RequestInfo_public_typeMaterial);
        TextView peso_view = (TextView) findViewById(R.id.RequestInfo_public_recicler);
        TextView volumen_view = (TextView) findViewById(R.id.RequestInfo_public_idPublication);
        TextView direccion_view = (TextView) findViewById(R.id.RequestInfo_public_date);
        TextView descripcion_view = (TextView) findViewById(R.id.RequestInfo_public_description);
        ImageView imagen_material = (ImageView) findViewById(R.id.RequestInfo_public_image);
        Button button = (Button) findViewById(R.id.RequestInfo_public_Accept);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarSolicitud(view);
            }
        });

        Button rute = (Button) findViewById(R.id.RequestInfo_Rute);
        rute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri.Builder builder= new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("origin", latitud + "," + longitud)
                        .appendQueryParameter("destination", direccion);
                String url= builder.build().toString();
                Log.d("Directions", url);
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


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

        ImageView home = (ImageView) findViewById(R.id.MenuRec_public_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Menu_rec.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });
    }

    public void enviarSolicitud(View view){
        APIService service = RetrofitClient.getApiService();
        Call<SolicitudRequest> call = service.registerSolicitud(
                Integer.parseInt(id),
                Integer.parseInt(id_publication)
        );
        call.enqueue(new Callback<SolicitudRequest>() {
            @Override
            public void onResponse(Call<SolicitudRequest> call, Response<SolicitudRequest> response) {
                String code= String.valueOf(response.raw().code());
                if(code.equals("201")){
                    Toast.makeText(getApplicationContext(),"Solicitud enviada",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Menu_rec.class);
                    intent.putExtra("token", token);
                    intent.putExtra("email", email);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Ya se han enviado solicitudes",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SolicitudRequest> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error en el servidor",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),Menu_rec.class);
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
            Intent intent=new Intent(getApplicationContext(), Menu_rec.class);
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