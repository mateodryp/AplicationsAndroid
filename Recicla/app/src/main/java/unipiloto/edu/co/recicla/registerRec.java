package unipiloto.edu.co.recicla;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import unipiloto.edu.co.recicla.models.Response;
import unipiloto.edu.co.recicla.remote.APIService;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class registerRec extends AppCompatActivity {

    private static final String CHANNEL_ID = "Canal";
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rec);
    }


    public void registerUserRec(View view){
        EditText first_name = (EditText) findViewById(R.id.regr_input_name);
        EditText last_name = (EditText) findViewById(R.id.regr_input_lname);
        EditText email = (EditText) findViewById(R.id.regr_input_email);
        EditText phone = (EditText) findViewById(R.id.regr_input_phone);
        EditText password = (EditText) findViewById(R.id.regr_input_passwd);
        EditText cpassword = (EditText) findViewById(R.id.regr_input_cpasswd);

        APIService service = RetrofitClient.getApiService();

        if(!first_name.getText().toString().equals("") & !last_name.getText().toString().equals("") & !email.getText().toString().equals("") & !phone.getText().toString().equals("") & !password.getText().toString().equals("") & !cpassword.getText().toString().equals("")){
            if(password.getText().toString().equals(cpassword.getText().toString())){
                if(password.getText().length() >= 8){


                Call<Response> call = service.registerRecUser(
                        first_name.getText().toString()
                        ,last_name.getText().toString()
                        ,email.getText().toString()
                        ,phone.getText().toString()
                        ,password.getText().toString());
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Log.e("Response", ""+response.raw().message());
                        String code= String.valueOf(response.raw().code());

                        if(code.equals("201")){
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            }else{
                            }

                            Toast.makeText(getApplicationContext(),"Usuario correctamente registrado",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),login.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Error al registrar el usuario",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener minimo 8 caracteres",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Por favor llenar todos los campos",Toast.LENGTH_LONG).show();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        showNewNotification();
    }

    private void showNewNotification(){
        setPendingIntent(login.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Registro Exitoso")
                .setContentText("Se ha registrado exitosamente")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());

    }

    private void setPendingIntent(Class<?> classAc){
        Intent intent = new Intent(getApplicationContext(), classAc);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(classAc);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}