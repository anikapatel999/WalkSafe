package com.example.walksafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CallSettingsActivity extends AppCompatActivity {

    public static final String TAG = "CallSettingsActivity";
    private TextView tvEmCall1;
    private TextView tvEmCall1Description;
    private EditText etEmCall1;
    private Button btnSave1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_settings);

        tvEmCall1 = findViewById(R.id.tvEmCall1);
        tvEmCall1Description = findViewById(R.id.tvEmCall1Description);
        etEmCall1 = findViewById(R.id.etEmCall1);
        btnSave1 = findViewById(R.id.btnSave1);
    }
}