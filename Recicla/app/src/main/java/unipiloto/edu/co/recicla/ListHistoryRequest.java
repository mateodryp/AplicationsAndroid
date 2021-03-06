package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unipiloto.edu.co.recicla.models.ChangePassword;
import unipiloto.edu.co.recicla.models.ListOwnRequest;
import unipiloto.edu.co.recicla.remote.RetrofitClient;

public class ListHistoryRequest extends AppCompatActivity {

    private String token;
    private String email;
    private String name;
    private String id;
    private ArrayList<ListOwnRequest> solicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history_request);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        ImageView home = (ImageView) findViewById(R.id.Rec_Sol_His_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Menu_rec.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        ImageView soli = (ImageView) findViewById(R.id.Rec_Sol_His_solicitudes);
        soli.setOnClickListener(new View.OnClickListener() {
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

        ImageView config = (ImageView) findViewById(R.id.Rec_Sol_His_config);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), changePasswordRecicler.class);
                intent.putExtra("token", token);
                intent.putExtra("email", email);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });



        Call<List<ListOwnRequest>> call = RetrofitClient.getApiService().getHistoryRequest(Integer.parseInt(id));
        call.enqueue(new Callback<List<ListOwnRequest>>() {
            @Override
            public void onResponse(Call<List<ListOwnRequest>> call, Response<List<ListOwnRequest>> response) {
                solicitudes = (ArrayList<ListOwnRequest>) response.body();
                ListView listView = (ListView) findViewById(R.id.Rec_Sol_His_list);
                MyAdapterOwnRequestPen adapter = new MyAdapterOwnRequestPen(getApplicationContext(), R.layout.list_view_solicitudes_pendientes, solicitudes);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListOwnRequest>> call, Throwable t) {

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