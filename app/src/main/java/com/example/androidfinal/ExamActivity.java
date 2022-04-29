package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.androidfinal.adapter.ExamGridViewAdapter;

public class ExamActivity extends AppCompatActivity {
    GridView gridView;
    int[] index={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        gridView = (GridView) findViewById(R.id.grid_view);

        ExamGridViewAdapter gridViewAdapter = new ExamGridViewAdapter(this,index);
        gridView.setAdapter(gridViewAdapter);
    }
}