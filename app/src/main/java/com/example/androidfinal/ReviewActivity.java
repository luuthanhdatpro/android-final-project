package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinal.model.Question;

import java.util.ArrayList;
import java.util.Random;

public class ReviewActivity extends AppCompatActivity {
    private TextView question, questionNum;
    private RadioButton rb1, rb2, rb3, rb4;
    private ArrayList<Question> questionList;
    private ImageButton bNext,bBack, bBTM;
    private RadioGroup rg;
    Random random;
    int currentScore = 0, questionAttemped = 1, currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        question = findViewById(R.id.tvQuestion);
        questionNum = findViewById(R.id.tvQuestionID);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        rg = findViewById(R.id.radioGroup);
        bNext = findViewById(R.id.button_next);
        bBack = findViewById(R.id.button_back);
        bBTM =  findViewById(R.id.button_btm);
        questionList = new ArrayList<>();
        random = new Random();
        getQuizQuestion(questionList);
        currentPos = 0;
        setDatatoView(currentPos);
        rb1.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb1.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Correct",Toast.LENGTH_LONG).show();
                rb1.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
            }
            else{
                Toast.makeText(ReviewActivity.this,"False",Toast.LENGTH_LONG).show();
                rb1.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb2.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb2.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Correct",Toast.LENGTH_LONG).show();
                rb2.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
            }
            else{
                Toast.makeText(ReviewActivity.this,"False",Toast.LENGTH_LONG).show();
                rb2.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb3.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb3.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Correct",Toast.LENGTH_LONG).show();
                rb3.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
          }
            else{
                Toast.makeText(ReviewActivity.this,"False",Toast.LENGTH_LONG).show();
                rb3.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb4.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb4.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Correct",Toast.LENGTH_LONG).show();
                rb4.setBackgroundColor(Color.GREEN);
                questionList.get(currentPos).setLearned(true);
            }
            else{
                Toast.makeText(ReviewActivity.this,"False",Toast.LENGTH_LONG).show();
                rb4.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        bBack.setOnClickListener(view -> {
            if(currentPos == 0)
                bBack.setClickable(false);
            for(int i = 0; i < rg.getChildCount(); i++){
                    rg.getChildAt(i).setClickable(true);
                    rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            rg.setBackgroundColor(Color.WHITE);
            bNext.setClickable(true);
            questionAttemped--;
            currentPos--;
            Log.i("TAG", String.valueOf(currentPos));
            setDatatoView(currentPos);
        });
        bNext.setOnClickListener(view -> {

            rg.clearCheck();
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(true);
                rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            if(currentPos == questionList.size()-2) {
                bNext.setClickable(false);
            }

            questionAttemped++;
            currentPos++;
            Log.i("current pos", String.valueOf(currentPos));
            setDatatoView(currentPos);
        });
        bBTM.setOnClickListener(view -> {
            Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void setDatatoView(int currentPos){
        if(questionList.get(currentPos).isLearned()==true) {
            questionNum.setText("Câu" + (currentPos + 1) + "/" + questionList.size() +" đã học");
        }else {
            questionNum.setText("Câu"+(currentPos+1)+"/4");
        }
        question.setText(questionList.get(currentPos).getTitle());
        rb1.setText(questionList.get(currentPos).getOption1());
        rb2.setText(questionList.get(currentPos).getOption2());
        rb3.setText(questionList.get(currentPos).getOption3());
        rb4.setText(questionList.get(currentPos).getOption4());

    }
    private void getQuizQuestion(ArrayList<Question> questionList){
        questionList.add(new Question("Test1","1.Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ","2.Wrong","3.Wrong","4.True",1));
        questionList.add(new Question("Test2","1.Wrong","2.True","3.Wrong","4.True",2));
        questionList.add(new Question("Test3","1.Wrong","2.Wrong","3.True","4.True",3));
        questionList.add(new Question("Test4","1.Wrong","2.Wrong","3.Wrong","4.True",4));
    }
}