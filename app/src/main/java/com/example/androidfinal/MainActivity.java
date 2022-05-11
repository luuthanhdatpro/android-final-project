package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    CardView cvDiemLiet,cvTest;
    ImageButton buttonTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cvDiemLiet =(CardView) findViewById(R.id.cv_diemliet);
        cvTest =(CardView) findViewById(R.id.cv_test);
        buttonTest = (ImageButton) findViewById(R.id.image_button_test);
        cvDiemLiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
        cvTest.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
            startActivity(intent);
        });
        buttonTest.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
            startActivity(intent);
        });
    }
}