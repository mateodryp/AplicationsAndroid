package unipiloto.edu.co.mymessinger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_create_message extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra("messageReceive");
        TextView messageView = (TextView) findViewById(R.id.textViewCreate);
        messageView.setText(messageText);
    }

    public void onSendMessage(View view){
        EditText messageView = (EditText) findViewById(R.id.messageCreate);
        String messageText = messageView.getText().toString();
        Intent intent = new Intent(this, activity_receive_message.class);
        intent.putExtra("messageCreate",messageText);
        startActivity(intent);
    }
}