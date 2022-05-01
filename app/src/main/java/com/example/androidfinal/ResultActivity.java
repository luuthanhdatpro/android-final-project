package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private ImageButton buttonExitExam;
    private Button buttonRetry;
    private TextView textViewRight, textViewWrong, textViewNotDone;
    private int[] doneQuestion;
    private int countRight,countWrong,countNot;
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
        countRight = 0;
        countWrong = 0;
        countNot = 0;
        for(int i = 0; i < doneQuestion.length; i++){
            if(doneQuestion[i]==0){
                countNot++;
            }else if(doneQuestion[i]==1){
                countRight++;
            }else{
                countWrong++;
            }
        }
        textViewRight.setText(countRight+" Đúng");
        textViewWrong.setText(countWrong+" Sai");
        textViewNotDone.setText(countNot+" Chưa trả lời");
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