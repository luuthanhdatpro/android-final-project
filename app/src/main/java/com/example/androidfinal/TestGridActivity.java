package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.androidfinal.adapter.ExamGridViewAdapter;

public class TestGridActivity extends AppCompatActivity {
    GridView gridView;
    String[] index={"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid2);
        gridView = findViewById(R.id.grid_view1);

        ExamGridViewAdapter gridViewAdapter = new ExamGridViewAdapter(this,index);
        gridView.setAdapter(gridViewAdapter);
    }
}