package com.example.beer_advisor;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.beer_advisor.BeerExpert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFindBeer(View view){

        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);
        String beerType = String.valueOf(color.getSelectedItem());
        String [] list = BeerExpert.getBrands(beerType);
        brands.setText(list[0] + "\n" +list[1] + "\n"+ list[2]);
    }
}