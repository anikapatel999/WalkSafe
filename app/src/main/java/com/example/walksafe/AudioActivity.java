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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;


public class AudioActivity extends AppCompatActivity {

    // fields for making calls
    EditText phoneNo;
    FloatingActionButton callbtn;
    static int PERMISSION_CODE= 100;

    TextView spokenWord;
    EditText editText;
    ImageView imageView;
    public final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    AlertDialog.Builder alertSpeechDialog;
    AlertDialog alertDialog;

    // fields for sending sms messages

    EditText textMessage;

    FloatingActionButton messagebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        // added functionality for making calls
        phoneNo = findViewById(R.id.editTextPhone);
        callbtn = findViewById(R.id.callbtn);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);

        // variables for sending sms messages
        textMessage = findViewById(R.id.editTextMessage);
        messagebtn = findViewById(R.id.messagebtn);
        if (ContextCompat.checkSelfPermission(AudioActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AudioActivity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);

        }

        // permissions for sending sms
        if (ContextCompat.checkSelfPermission(AudioActivity.this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AudioActivity.this,new String[]{Manifest.permission.SEND_SMS},PERMISSION_CODE);

        }

        if (ContextCompat.checkSelfPermission(AudioActivity.this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AudioActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},PERMISSION_CODE);

        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(AudioActivity.this).inflate(R.layout.alertcustom,
                        viewGroup, false);

                alertSpeechDialog = new AlertDialog.Builder(AudioActivity.this);
                alertSpeechDialog.setMessage("Listening...");
                alertSpeechDialog.setView(dialogView);
                alertDialog = alertSpeechDialog.create();
                alertDialog.show();
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                imageView.setImageResource(R.drawable.ic_baseline_mic_24);
                ArrayList<String> arrayList = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(arrayList.get(0));

                alertDialog.dismiss();
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        imageView.setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                speechRecognizer.stopListening();
            }
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                imageView.setImageResource(R.drawable.ic_baseline_mic_24);
                speechRecognizer.startListening(speechIntent);
            }
            return false;
        });

        // call click onclick
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();

            }
        });

        messagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();

            }
        });

    }

    // private method for making phone calls
    private void makeCall(){
        // making phone calls
        String phoneno = phoneNo.getText().toString();
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+phoneno));
        startActivity(i);
    }

    // private method for sending sms messages
    @SuppressLint("ClickableViewAccessibility")
    private void sendSMS(){
        // making phone calls
        String phoneno = phoneNo.getText().toString();
        String message = textMessage.getText().toString();

//        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//        sendIntent.putExtra("sms_body", "default content");
//        sendIntent.setType("vnd.android-dir/mms-sms");
//        startActivity(sendIntent);
        if (!phoneno.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneno, null, message, null, null);

            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show();
        }


        // speech recognition that works



//        callbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String phoneno = phoneNo.getText().toString();
//                Intent i = new Intent(Intent.ACTION_CALL);
//                i.setData(Uri.parse("tel:"+phoneno));
//                startActivity(i);
//
//            }
//        });

    }

    private void checkPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(AudioActivity.this, new String[]{
                    Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        speechRecognizer.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==RecordAudioRequestCode && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        menu.findItem(R.id.audio).setVisible(false);
        return true;
    }

    public void onLogout(MenuItem item) {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Intent intent = new Intent(AudioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCallSettings(MenuItem item) {
        Intent intent = new Intent(AudioActivity.this, CallSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onMessageSettings(MenuItem item) {
        Intent intent = new Intent(AudioActivity.this, MessageSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onHome(MenuItem item) {
        Intent intent = new Intent(AudioActivity.this, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onAudio(MenuItem item) {
        Intent intent = new Intent(AudioActivity.this, AudioActivity.class);
        startActivity(intent);
//        finish();
    }
}