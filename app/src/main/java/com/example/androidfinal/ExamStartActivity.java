package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.androidfinal.adapter.ExamGridViewAdapter;

public class ExamStartActivity extends AppCompatActivity {
    Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_start);
        buttonStart = (Button) findViewById(R.id.button_start_exam);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamStartActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });
    }
}