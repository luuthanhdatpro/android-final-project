package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ResultActivity extends AppCompatActivity {
    ImageButton buttonExitExam;
    Button buttonRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        function();
    }
    private void init(){
        buttonExitExam = findViewById(R.id.button_exit_result);
        buttonRetry = findViewById(R.id.button_retry);
    }
    private void function(){
        buttonExitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}