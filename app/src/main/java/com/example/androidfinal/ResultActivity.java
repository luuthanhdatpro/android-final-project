package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView textViewRight, textViewWrong, textViewNotDone, textViewResult;
    private int[] doneQuestion, essQuestion;
    private ArrayList<Question> questionList, rightAns, wrongAns, notAns;
    private int countRight, countWrong, countNot, countEss;
    private RecyclerView recyclerView;
    private ExamResultAdapter examResultAdapter;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        resultProcess();
        function();
    }
    private void init(){
        textViewResult = findViewById(R.id.tv_final_result);
        buttonExitExam = findViewById(R.id.button_exit_result);
        buttonRetry = findViewById(R.id.button_retry);
        textViewRight = findViewById(R.id.tv_rightanswer);
        textViewWrong = findViewById(R.id.tv_wronganswer);
        textViewNotDone = findViewById(R.id.tv_notdoneanswer);
        Intent intent = this.getIntent();
        essQuestion = intent.getIntArrayExtra("essQuestion");
        doneQuestion = intent.getIntArrayExtra("doneQuestion");
        questionList = (ArrayList<Question>) intent.getSerializableExtra("questionList");
        rightAns = new ArrayList<>();
        wrongAns = new ArrayList<>();
        notAns = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_result);
        countRight = 0;
        countEss = 0;
        countWrong = 0;
        countNot = 0;
        sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
    }
    private void resultProcess(){
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

            if(essQuestion[i]==1)
                countEss++;
        }
        textViewRight.setText(countRight+" Đúng");
        textViewWrong.setText(countWrong+" Sai");
        textViewNotDone.setText(countNot+" Chưa trả lời");
        Log.i("Nuull or not", String.valueOf(questionList.size()));
        examResultAdapter = new ExamResultAdapter(questionList, question -> {

        },doneQuestion,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        String type = sharedPreferences.getString("type",null);
        examResultAdapter.setData(questionList);
        recyclerView.setAdapter(examResultAdapter);
        if (type.equals("B2")) {
            if (countRight < 32 || countEss > 0) {
                textViewResult.setText("Bạn đã thi trượt");
            } else {
                textViewResult.setText("Bạn đã thi đỗ");
                textViewResult.setBackgroundColor(R.color.green);
            }
        }else{
            if (countRight < 27 || countEss > 0) {
                textViewResult.setText("Bạn đã thi trượt");
            } else {
                textViewResult.setText("Bạn đã thi đỗ");
                textViewResult.setBackgroundColor(R.color.green);
            }
        }

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
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, ExamStartActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}