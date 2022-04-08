package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startBottom);
        EditText inputName = findViewById(R.id.editName);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Questions.class);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, 1);
                    finish();
            }
        });
    }
}
