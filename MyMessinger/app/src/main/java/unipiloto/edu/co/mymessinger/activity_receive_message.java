package unipiloto.edu.co.mymessinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_receive_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra("messageCreate");
        TextView messageView = (TextView) findViewById(R.id.textViewReceive);
        messageView.setText(messageText);
    }

    public void onSendMessage(View view){
        EditText messageView = (EditText) findViewById(R.id.messageReceive);
        String messageText = messageView.getText().toString();
        Intent intent = new Intent(this, activity_create_message.class);
        intent.putExtra("messageReceive",messageText);
        startActivity(intent);
    }
}