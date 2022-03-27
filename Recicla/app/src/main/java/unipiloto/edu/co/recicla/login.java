package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import unipiloto.edu.co.recicla.models.Response;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickRegister(View view){
        Intent intent = new Intent(getApplicationContext(), selectTypeUser.class);
        startActivity(intent);
    }

    public void login(View view){
        EditText email = (EditText) findViewById(R.id.loginEmail);
        EditText password = (EditText) findViewById(R.id.loginPasswd);

        APIService service = RetrofitClient.getApiService();

        if( !email.getText().toString().equals("") &  !password.getText().toString().equals("")){

            Call<Response> call = service.login(
                    email.getText().toString()
                    ,password.getText().toString());
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Log.e("Response", ""+response.raw().message());
                    String code= String.valueOf(response.raw().code());

                    if(code.equals("201")){
                        Intent intent=new Intent(getApplicationContext(), Menu_rec.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e("Error", ""+t.toString());
                    Toast.makeText(getApplicationContext(),"Error al conectarse al servidor",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();
                }
            });




        }else{
            Toast.makeText(getApplicationContext(),"Por favor llenar todos los campos",Toast.LENGTH_LONG).show();
        }


    }
}