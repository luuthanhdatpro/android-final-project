package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidfinal.adapter.ExamGridViewAdapter;

public class ExamStartActivity extends AppCompatActivity {
    Button buttonStart;
    TextView tvType, tvExamStart;
    ImageButton imageButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_start);
        buttonStart = (Button) findViewById(R.id.button_start_exam);
        tvType = findViewById(R.id.tv_exam_start_type);
        imageButton = findViewById(R.id.button_exit_start);
        tvExamStart = findViewById(R.id.tv_exam_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamStartActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
        String type = sharedPreferences.getString("type",null);
        tvType.setText("Bài thi thử lý thuyết \n GPLX hạng "+ type);
        if(type.equals("B1"))
            tvExamStart.setText("Tổng số câu: 30\nThời gian làm bài: 20 phút\nSố câu đúng tối thiểu: 27/30\n" +
                "Câu hỏi điểm liệt: Sai một câu bất kỳ trong nhóm câu liệt, học viên sẽ trượt bài thi.");
        else
            tvExamStart.setText("Tổng số câu: 35\nThời gian làm bài: 22 phút\nSố câu đúng tối thiểu: 32/35\n" +
                    "Câu hỏi điểm liệt: Sai một câu bất kỳ trong nhóm câu liệt, học viên sẽ trượt bài thi.");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamStartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}