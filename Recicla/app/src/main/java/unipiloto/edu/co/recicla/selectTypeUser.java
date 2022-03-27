package unipiloto.edu.co.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selectTypeUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_user);
    }

    public void onClickRegisterRec(View view){
        Intent intent = new Intent(getApplicationContext(), registerRec.class);
        startActivity(intent);
    }

    public void onClickRegisterProv(View view){
        Intent intent = new Intent(getApplicationContext(), registerProv.class);
        startActivity(intent);
    }
}