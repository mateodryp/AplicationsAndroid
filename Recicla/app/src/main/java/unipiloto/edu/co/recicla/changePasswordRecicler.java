package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import unipiloto.edu.co.recicla.models.ChangePassword;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class changePasswordRecicler extends AppCompatActivity {

    private String token;
    private String email;
    private String name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_recicler);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        EditText password = (EditText) findViewById(R.id.CPR_new_password);
        EditText cpassword = (EditText) findViewById(R.id.CPR_new_password_confirm);

        ImageView home = (ImageView)  findViewById(R.id.CPR_home);
        ImageView solicitudes = (ImageView)  findViewById(R.id.CPR_solicitudes);
        ImageView historial = (ImageView)  findViewById(R.id.CPR_history);

        solicitudes.setOnClickListener(new View.OnClickListener() {
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

        Button change = (Button)  findViewById(R.id.CPR_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals("")&!cpassword.getText().toString().equals("")){
                    if(password.getText().toString().equals(cpassword.getText().toString())){

                        Call<ChangePassword> call = RetrofitClient.getApiService().changePassword(Integer.parseInt(id),password.getText().toString() );
                        call.enqueue(new Callback<ChangePassword>() {
                            @Override
                            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                                String code= String.valueOf(response.raw().code());
                                if(code.equals("201")){
                                    Toast.makeText(getApplicationContext(),"Contraseña cambiada exitosamente ",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(),Menu_rec.class);
                                    intent.putExtra("token", token);
                                    intent.putExtra("email", email);
                                    intent.putExtra("id", id);
                                    intent.putExtra("name", name);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Error al cambiar contraseña",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ChangePassword> call, Throwable t) {

                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(),"Contraseñas no coinciden",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),login.class);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Por Favor llene todos los campos",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),login.class);
                }
            }
        });
    }
}