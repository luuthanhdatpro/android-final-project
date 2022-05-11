package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidfinal.adapter.ExamListAdapter;
import com.example.androidfinal.inteface.ExamListOnClickListener;
import com.example.androidfinal.model.Exam;

import java.util.ArrayList;

public class ExamListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExamListAdapter examListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        recyclerView = findViewById(R.id.recycle_exam_list);
        examListAdapter = new ExamListAdapter(getExamList(), new ExamListOnClickListener() {
            @Override
            public void clickItem(Exam exam) {
                Intent intent = new Intent(ExamListActivity.this, ExamStartActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        examListAdapter.setData(getExamList());
        recyclerView.setAdapter(examListAdapter);
    }

    private ArrayList<Exam> getExamList(){
        ArrayList<Exam> list = new ArrayList<>();
        list.add(new Exam(1,"Đề ngẫu nhiên"));
        list.add(new Exam(2,"Đề số 1"));
        list.add(new Exam(3,"Đề số 2"));
        list.add(new Exam(4,"Đề số 3"));

        return list;
    }
}