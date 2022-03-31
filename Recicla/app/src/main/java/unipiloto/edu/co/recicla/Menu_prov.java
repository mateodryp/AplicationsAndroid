package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu_prov extends AppCompatActivity {
    private String token;
    private String email;
    private String name;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prov);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        TextView nameText = (TextView) findViewById(R.id.menuProv_name);
        nameText.setText(name);
    }


    public void onClickPublicar(View view){
        Intent intent=new Intent(getApplicationContext(),PublicarMaterial.class);
        intent.putExtra("token", token);
        intent.putExtra("email", email);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}