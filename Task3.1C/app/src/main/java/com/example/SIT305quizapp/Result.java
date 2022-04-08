package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String answers = intent.getStringExtra("answers");
        String name = intent.getStringExtra("name");
        TextView congratsTextView = findViewById(R.id.congratulations);
        String text = congratsTextView.getText().toString();
        congratsTextView.setText(text + ' ' + name.toString());
        TextView scoreTextView = findViewById(R.id.totalScore);
        scoreTextView.setText(answers.toString() + "/5");
        Button newQuizButton = findViewById(R.id.restartBottom);
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Questions.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
                finish();
            }
        });
    }

    public void finishButtonClick(View view) {
        finish();
    }
}