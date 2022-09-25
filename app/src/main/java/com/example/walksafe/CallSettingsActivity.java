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

public class CallSettingsActivity extends AppCompatActivity {

    public static final String TAG = "CallSettingsActivity";

    private TextView tvEmCall1;
    private TextView tvEmCall1Description;
    private EditText etEmCall1;
    private Button btnCallSave1;
    private TextView tvEmWord1Description;
    private EditText etEmWord1;
    private Button btnWordSave1;

    private TextView tvEmCall2;
    private TextView tvEmCall2Description;
    private EditText etEmCall2;
    private Button btnCallSave2;
    private TextView tvEmWord2Description;
    private EditText etEmWord2;
    private Button btnWordSave2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_settings);

        tvEmCall1 = findViewById(R.id.tvEmCall1);
        tvEmCall1Description = findViewById(R.id.tvEmCall1Description);
        etEmCall1 = findViewById(R.id.etEmCall1);
        btnCallSave1 = findViewById(R.id.btnCallSave1);
        tvEmWord1Description = findViewById(R.id.tvEmWord1Description);
        etEmWord1 = findViewById(R.id.etEmWord1);
        btnWordSave1 = findViewById(R.id.btnWordSave1);

        tvEmCall2 = findViewById(R.id.tvEmCall2);
        tvEmCall2Description = findViewById(R.id.tvEmCall2Description);
        etEmCall2 = findViewById(R.id.etEmCall2);
        btnCallSave2 = findViewById(R.id.btnCallSave2);
        tvEmWord2Description = findViewById(R.id.tvEmWord2Description);
        etEmWord2 = findViewById(R.id.etEmWord2);
        btnWordSave2 = findViewById(R.id.btnWordSave2);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String currentNumber1 = currentUser.get("firstCall").toString();
        etEmCall1.setText(currentNumber1);

        String currentWord1 = "";

        if (Objects.equals(currentUser.get("setFirstCallWord"), true)) {
            currentWord1 = currentUser.get("firstCallWord").toString();
            etEmWord1.setText(currentWord1);
        }

        btnCallSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callNumber1 = etEmCall1.getText().toString();
                if (!(callNumber1.matches("[0-9]+"))){
                    Toast.makeText(CallSettingsActivity.this, "Phone number must contain numbers only", Toast.LENGTH_SHORT).show();
                }
                else if (callNumber1.equals("911") || callNumber1.equals("988") || callNumber1.length() == 10) {
                    currentUser.put("firstCall", callNumber1);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(CallSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(CallSettingsActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWordSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callWord1 = etEmWord1.getText().toString();
                if (!(callWord1.isEmpty())) {
                    currentUser.put("firstCallWord", callWord1);
                    if (Objects.equals(currentUser.get("setFirstCallWord"), false)) {
                        currentUser.put("setFirstCallWord", true);
                    }
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(CallSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm" + callWord1);
                        }
                    });
                }
                else {
                    Toast.makeText(CallSettingsActivity.this, "Please enter a valid word!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // PERSONAL CALL SECTION

        String currentNumber2 = currentUser.get("secondCall").toString();
        etEmCall2.setText(currentNumber2);

        String currentWord2 = "";

        if (Objects.equals(currentUser.get("setSecondCallWord"), true)) {
            currentWord2 = currentUser.get("secondCallWord").toString();
            etEmWord2.setText(currentWord2);
        }

        btnCallSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callNumber2 = etEmCall2.getText().toString();
                if (!(callNumber2.matches("[0-9]+"))){
                    Toast.makeText(CallSettingsActivity.this, "Phone number must contain numbers only", Toast.LENGTH_SHORT).show();
                }
                else if (callNumber2.equals("911") || callNumber2.equals("988") || callNumber2.length() == 10) {
                    currentUser.put("secondCall", callNumber2);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(CallSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(CallSettingsActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWordSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callWord2 = etEmWord2.getText().toString();
                if (!(callWord2.isEmpty())) {
                    currentUser.put("secondCallWord", callWord2);
                    if (Objects.equals(currentUser.get("setSecondCallWord"), false)) {
                        currentUser.put("setSecondCallWord", true);
                    }
                     currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(CallSettingsActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "sdmdfkm" + callWord2);
                        }
                    });
                }
                else {
                    Toast.makeText(CallSettingsActivity.this, "Please enter a valid word!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        menu.findItem(R.id.callSettings).setVisible(false);
        return true;
    }

    public void onLogout(MenuItem item) {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Intent intent = new Intent(CallSettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCallSettings(MenuItem item) {
        Intent intent = new Intent(CallSettingsActivity.this, CallSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onMessageSettings(MenuItem item) {
        Intent intent = new Intent(CallSettingsActivity.this, MessageSettingsActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onHome(MenuItem item) {
        Intent intent = new Intent(CallSettingsActivity.this, MainActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onAudio(MenuItem item) {
        Intent intent = new Intent(CallSettingsActivity.this, AudioActivity.class);
        startActivity(intent);
//        finish();
    }

}
