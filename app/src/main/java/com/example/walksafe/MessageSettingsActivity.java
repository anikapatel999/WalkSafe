package com.example.walksafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Objects;

public class MessageSettingsActivity extends AppCompatActivity {

    public static final String TAG = "MessageSettingsActivity";

    private TextView tvEmMessage1;
    private TextView tvEmMessage1Description;
    private EditText etEmMessage1;
    private Button btnMessageSave1;
    private TextView tvEmBody1Description;
    private EditText etEmBody1;
    private Button btnBodySave1;
    private TextView tvEmWord1Description;
    private EditText etEmWord1;
    private Button btnWordSave1;

    private TextView tvEmMessage2;
    private TextView tvEmMessage2Description;
    private EditText etEmMessage2;
    private Button btnMessageSave2;
    private TextView tvEmBody2Description;
    private EditText etEmBody2;
    private Button btnBodySave2;
    private TextView tvEmWord2Description;
    private EditText etEmWord2;
    private Button btnWordSave2;

    private TextView tvEmMessage3;
    private TextView tvEmMessage3Description;
    private EditText etEmMessage3;
    private Button btnMessageSave3;
    private TextView tvEmBody3Description;
    private EditText etEmBody3;
    private Button btnBodySave3;
    private TextView tvEmWord3Description;
    private EditText etEmWord3;
    private Button btnWordSave3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_settings);

        tvEmMessage1 = findViewById(R.id.tvEmMessage1);
        tvEmMessage1Description = findViewById(R.id.tvEmMessage1Description);
        etEmMessage1 = findViewById(R.id.etEmMessage1);
        btnMessageSave1 = findViewById(R.id.btnMessageSave1);
        tvEmBody1Description = findViewById(R.id.tvEmBody1Description);
        etEmBody1 = findViewById(R.id.etEmBody1);
        btnBodySave1 = findViewById(R.id.btnBodySave1);
        tvEmWord1Description = findViewById(R.id.tvEmWord1Description);
        etEmWord1 = findViewById(R.id.etEmWord1);
        btnWordSave1 = findViewById(R.id.btnWordSave1);

        tvEmMessage2 = findViewById(R.id.tvEmMessage2);
        tvEmMessage2Description = findViewById(R.id.tvEmMessage2Description);
        etEmMessage2 = findViewById(R.id.etEmMessage2);
        btnMessageSave2 = findViewById(R.id.btnMessageSave2);
        tvEmBody2Description = findViewById(R.id.tvEmBody2Description);
        etEmBody2 = findViewById(R.id.etEmBody2);
        btnBodySave2 = findViewById(R.id.btnBodySave2);
        tvEmWord2Description = findViewById(R.id.tvEmWord2Description);
        etEmWord2 = findViewById(R.id.etEmWord2);
        btnWordSave2 = findViewById(R.id.btnWordSave2);

        tvEmMessage3 = findViewById(R.id.tvEmMessage3);
        tvEmMessage3Description = findViewById(R.id.tvEmMessage3Description);
        etEmMessage3 = findViewById(R.id.etEmMessage3);
        btnMessageSave3 = findViewById(R.id.btnMessageSave3);
        tvEmBody3Description = findViewById(R.id.tvEmBody3Description);
        etEmBody3 = findViewById(R.id.etEmBody3);
        btnBodySave3 = findViewById(R.id.btnBodySave3);
        tvEmWord3Description = findViewById(R.id.tvEmWord3Description);
        etEmWord3 = findViewById(R.id.etEmWord3);
        btnWordSave3 = findViewById(R.id.btnWordSave3);


        ParseUser currentUser = ParseUser.getCurrentUser();

        // MESSAGE 1

        String currentNumber1 = currentUser.get("textNumber1").toString();
        etEmMessage1.setText(currentNumber1);

        String currentBody1 = currentUser.get("setFirstMessageBody").toString();
        etEmBody1.setText(currentBody1);

        String currentWord1 = "";

        if (currentUser.get("setMessage1Word").equals(true)) {
            currentWord1 = currentUser.get("textWord1").toString();
            etEmWord1.setText(currentWord1);
        }

        btnMessageSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageNumber1 = etEmMessage1.getText().toString();
                if (!(messageNumber1.matches("[0-9]+"))){
                    Toast.makeText(MessageSettingsActivity.this, "Phone number must contain numbers only", Toast.LENGTH_SHORT).show();
                }
                else if (messageNumber1.equals("911") || messageNumber1.equals("988") || messageNumber1.length() == 10) {
                    currentUser.put("textNumber1", messageNumber1);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBodySave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageBody1 = etEmBody1.getText().toString();
                if (!(messageBody1.isEmpty())) {
                    currentUser.put("setFirstMessageBody", messageBody1);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm " + messageBody1);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Message cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWordSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageWord1 = etEmWord1.getText().toString();
                if (!(messageWord1.isEmpty())) {
                    currentUser.put("textWord1", messageWord1);
                    if (currentUser.get("setMessage1Word").equals(false)) {
                        currentUser.put("setMessage1Word", true);
                    }
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm" + messageWord1);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Please enter a valid word!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // MESSAGE 2

        String currentNumber2 = currentUser.get("textNumber2").toString();
        etEmMessage2.setText(currentNumber2);

        String currentBody2 = currentUser.get("setSecondMessageBody").toString();
        etEmBody2.setText(currentBody2);

        String currentWord2 = "";

        if (currentUser.get("setMessage2Word").equals(true)) {
            currentWord2 = currentUser.get("textWord2").toString();
            etEmWord2.setText(currentWord2);
        }

        btnMessageSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageNumber2 = etEmMessage2.getText().toString();
                if (!(messageNumber2.matches("[0-9]+"))){
                    Toast.makeText(MessageSettingsActivity.this, "Phone number must contain numbers only", Toast.LENGTH_SHORT).show();
                }
                else if (messageNumber2.equals("911") || messageNumber2.equals("988") || messageNumber2.length() == 10) {
                    currentUser.put("textNumber2", messageNumber2);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBodySave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageBody2 = etEmBody2.getText().toString();
                if (!(messageBody2.isEmpty())) {
                    currentUser.put("setSecondMessageBody", messageBody2);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm " + messageBody2);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Message cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWordSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageWord2 = etEmWord2.getText().toString();
                if (!(messageWord2.isEmpty())) {
                    currentUser.put("textWord2", messageWord2);
                    if (currentUser.get("setMessage2Word").equals(false)) {
                        currentUser.put("setMessage2Word", true);
                    }
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm" + messageWord2);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Please enter a valid word!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // MESSAGE 3

        String currentNumber3 = currentUser.get("textNumber3").toString();
        etEmMessage3.setText(currentNumber3);

        String currentBody3 = currentUser.get("setThirdMessageBody").toString();
        etEmBody3.setText(currentBody3);

        String currentWord3 = "";

        if (currentUser.get("setMessage3Word").equals(true)) {
            currentWord3 = currentUser.get("textWord3").toString();
            etEmWord3.setText(currentWord3);
        }

        btnMessageSave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageNumber3 = etEmMessage3.getText().toString();
                if (!(messageNumber3.matches("[0-9]+"))){
                    Toast.makeText(MessageSettingsActivity.this, "Phone number must contain numbers only", Toast.LENGTH_SHORT).show();
                }
                else if (messageNumber3.equals("911") || messageNumber3.equals("988") || messageNumber3.length() == 10) {
                    currentUser.put("textNumber3", messageNumber3);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBodySave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageBody3 = etEmBody3.getText().toString();
                if (!(messageBody3.isEmpty())) {
                    currentUser.put("setThirdMessageBody", messageBody3);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm " + messageBody3);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Message cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWordSave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageWord3 = etEmWord3.getText().toString();
                if (!(messageWord3.isEmpty())) {
                    currentUser.put("textWord3", messageWord3);
                    if (currentUser.get("setMessage3Word").equals(false)) {
                        currentUser.put("setMessage3Word", true);
                    }
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(MessageSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm" + messageWord3);
                        }
                    });
                }
                else {
                    Toast.makeText(MessageSettingsActivity.this, "Please enter a valid word!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        menu.findItem(R.id.messageSettings).setVisible(false);
        return true;
    }

    public void onLogout(MenuItem item) {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Intent intent = new Intent(MessageSettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCallSettings(MenuItem item) {
        Intent intent = new Intent(MessageSettingsActivity.this, CallSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onMessageSettings(MenuItem item) {
        Intent intent = new Intent(MessageSettingsActivity.this, MessageSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onHome(MenuItem item) {
        Intent intent = new Intent(MessageSettingsActivity.this, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onAudio(MenuItem item) {
        Intent intent = new Intent(MessageSettingsActivity.this, AudioActivity.class);
        startActivity(intent);
//        finish();
    }

}
