package com.example.week5intents;

import ndroidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText2, editText3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        SendSMS();;
                    } else  {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},1 );
                    }
                }
            }
        });
    }


    private void SendSMS() {
        String number = editText2.getText().toString().trim();
        String message = editText3.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            //message
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Message is not sent", Toast.LENGTH_SHORT).show();
        }
    }
}
