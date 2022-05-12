package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidfinal.adapter.ExamResultAdapter;
import com.example.androidfinal.inteface.ExamResultOnClickListener;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ImageButton buttonExitExam;
    private Button buttonRetry;
    private TextView textViewRight, textViewWrong, textViewNotDone;
    private int[] doneQuestion;
    private ArrayList<Question> questionList, rightAns, wrongAns, notAns;
    private int countRight,countWrong,countNot;
    private RecyclerView recyclerView;
    private ExamResultAdapter examResultAdapter;
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
        textViewRight = findViewById(R.id.tv_rightanswer);
        textViewWrong = findViewById(R.id.tv_wronganswer);
        textViewNotDone = findViewById(R.id.tv_notdoneanswer);
        Intent intent = this.getIntent();
        doneQuestion = intent.getIntArrayExtra("doneQuestion");
        questionList = (ArrayList<Question>) intent.getSerializableExtra("questionList");
        rightAns = new ArrayList<>();
        wrongAns = new ArrayList<>();
        notAns = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_result);
        countRight = 0;
        countWrong = 0;
        countNot = 0;
        for(int i = 0; i < doneQuestion.length; i++){
            if(doneQuestion[i]==0){
                notAns.add(questionList.get(i));
                countNot++;
            }else if(doneQuestion[i]==1){
                rightAns.add(questionList.get(i));
                countRight++;
            }else{
                wrongAns.add(questionList.get(i));
                countWrong++;
            }
        }
        textViewRight.setText(countRight+" Đúng");
        textViewWrong.setText(countWrong+" Sai");
        textViewNotDone.setText(countNot+" Chưa trả lời");
        Log.i("Nuull or not", String.valueOf(questionList.size()));
        examResultAdapter = new ExamResultAdapter(questionList, question -> {

        },doneQuestion);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        examResultAdapter.setData(questionList);
        recyclerView.setAdapter(examResultAdapter);
    }
    private void function(){
        buttonExitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        textViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examResultAdapter.setData(rightAns,doneQuestion);
                //recyclerView.setAdapter(examResultAdapter);
            }
        });
        textViewWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examResultAdapter.setData(wrongAns,doneQuestion);
            }
        });
        textViewNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examResultAdapter.setData(notAns,doneQuestion);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}