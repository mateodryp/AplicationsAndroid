package unipiloto.edu.co.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class stopwatch extends Activity {

    private int seconds = 0;
    private boolean running;
    private int laps=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickLap(View view){
        if(laps < 5){
            final TextView lapView = (TextView) findViewById(R.id.lap_view);
            int hours = seconds/3600;
            int minutes = (seconds%3600)/60;
            int secs = seconds%60;
            String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

            switch (laps){
                case 0:
                    lapView.setText("vuelta 1: " + time);
                    break;
                case 1:
                    lapView.setText(lapView.getText() +"\n"+ "vuelta 2: " + time);
                    break;
                case 2:
                    lapView.setText(lapView.getText() +"\n"+ "vuelta 3: " + time);
                    break;
                case 3:
                    lapView.setText(lapView.getText() +"\n"+ "vuelta 4: " + time);
                    break;
                case 4:
                    lapView.setText(lapView.getText() +"\n"+ "vuelta 5: " + time);
                    break;
            }
            laps++;
        }
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        final TextView lapView = (TextView) findViewById(R.id.lap_view);
        running = false;
        seconds = 0;
        laps = 0;
        lapView.setText("");

    }

    public void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });



    }
}