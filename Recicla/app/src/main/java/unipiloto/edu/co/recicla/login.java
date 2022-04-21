package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.LoginRequest;
import unipiloto.edu.co.recicla.models.LoginResponse;
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
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(email.getText().toString());
            loginRequest.setPassword(password.getText().toString());


            Call<LoginResponse> loginResponseCall = RetrofitClient.getApiService().userLogin(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    String code= String.valueOf(response.raw().code());
                    if(code.equals("201")){

                        LoginResponse loginresponse = response.body();
                        if(loginresponse.isProvider()){
                            Intent intent=new Intent(getApplicationContext(), Menu_prov.class);
                            intent.putExtra("token", loginresponse.getToken());
                            intent.putExtra("email", loginresponse.getEmail());
                            String prueba =  String.valueOf(loginresponse.getId());
                            intent.putExtra("id", String.valueOf(loginresponse.getId()));
                            intent.putExtra("name", loginresponse.getName() +" "+  loginresponse.getLast_name());
                            startActivity(intent);
                            finish();
                        }else{
                            if(loginresponse.isRecycler()){
                                Intent intent=new Intent(getApplicationContext(), Menu_rec.class);
                                intent.putExtra("token", loginresponse.getToken());
                                intent.putExtra("email", loginresponse.getEmail());
                                intent.putExtra("id", String.valueOf(loginresponse.getId()));
                                intent.putExtra("name", loginresponse.getName() +" "+  loginresponse.getLast_name());
                                startActivity(intent);
                                finish();
                            }
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
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

