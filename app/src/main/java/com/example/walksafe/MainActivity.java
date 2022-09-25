package com.example.walksafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

// added imports for making calls
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private Button call1;
    private Button call2;
    private Button message1;
    private Button message2;
    private Button message3;

    static int PERMISSION_CODE = 100;

    private ParseUser user = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call1 = findViewById(R.id.call1);
        call2 = findViewById(R.id.call2);
        message1 = findViewById(R.id.message1);
        message2 = findViewById(R.id.message2);
        message3 = findViewById(R.id.message3);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);

        }

        // permissions for sending sms
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_CODE);

        }

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall("firstCall");
            }
        });


        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall("secondCall");
            }
        });


        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("textNumber1", "setFirstMessageBody");
            }
        });

        message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("textNumber2", "setSecondMessageBody");
            }
        });


        message3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("textNumber3", "setThirdMessageBody");
            }
        });

    }

    // private method for making phone calls
    private void makeCall(String phoneNumColName) {

        // making phone calls
        String phoneno = user.get(phoneNumColName).toString();
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + phoneno));
        startActivity(i);

    }

    // private method for sending sms messages
    private void sendSMS(String phoneNumColName, String messageColName) {
        // making phone calls
        String phoneno = user.get(phoneNumColName).toString();
        String message = user.get(messageColName).toString();

//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(phoneno, null, message, null, null);

        if (!phoneno.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneno, null, message, null, null);

            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        menu.findItem(R.id.home).setVisible(false);
        return true;
    }

    public void onLogout(MenuItem item) {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCallSettings(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, CallSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onMessageSettings(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, MessageSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onAudio(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AudioActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onHome(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
//        finish();
    }
}