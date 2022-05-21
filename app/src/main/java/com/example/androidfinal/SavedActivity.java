package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinal.database.DatabaseHelper;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {
    private TextView question, questionNum;
    private RadioButton rb1, rb2, rb3, rb4;
    private ArrayList<Question> questionList;
    private ImageButton bNext,bBack, bBTM, bSave;
    private RadioGroup rg;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int questionAttemped = 1, currentPos;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        init();
        databaseHelper = new DatabaseHelper(this);
        questionList = databaseHelper.getSavedQuestion();
        for(int i = 0; i < questionList.size(); i++) {
            questionList.get(i).setTempID(i+1);
        }
        currentPos = 0;
        setDatatoView(currentPos);
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        function();
    }
    private void init(){
        question = findViewById(R.id.tvQuestion_saved);
        questionNum = findViewById(R.id.tvQuestionID_saved);
        rb1 = findViewById(R.id.option1_saved);
        rb2 = findViewById(R.id.option2_saved);
        rb3 = findViewById(R.id.option3_saved);
        rb4 = findViewById(R.id.option4_saved);
        rg = findViewById(R.id.radioGroup_saved);
        bNext = findViewById(R.id.button_next_saved);
        bBack = findViewById(R.id.button_back_saved);
        bBTM =  findViewById(R.id.button_btm_saved);
        bSave = findViewById(R.id.button_save_saved);
        questionList = new ArrayList<>();
    }
    private void function(){
        rb1.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb1.getText().toString().substring(0,1))){
                Toast.makeText(SavedActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb1.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),1).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(SavedActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb1.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb2.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb2.getText().toString().substring(0,1))){
                Toast.makeText(SavedActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb2.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),2).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(SavedActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb2.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb3.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb3.getText().toString().substring(0,1))){
                Toast.makeText(SavedActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb3.setBackgroundColor(Color.GREEN);
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),3).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(SavedActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb3.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb4.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb4.getText().toString().substring(0,1))){
                Toast.makeText(SavedActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb4.setBackgroundColor(Color.GREEN);
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),4).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(SavedActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb4.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        bBack.setOnClickListener(view -> {

            bNext.setClickable(true);
            if(currentPos > 0){
                bBack.setClickable(true);
                currentPos--;
                setDatatoView(currentPos);
            }else{
                bBack.setClickable(false);
            }
            rg.clearCheck();
            for (int i = 0; i < rg.getChildCount(); i++) {
                rg.getChildAt(i).setClickable(true);
                rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            rg.setBackgroundColor(Color.WHITE);
            questionAttemped--;

            Log.i("current pos bBack", String.valueOf(currentPos));
            int choice = sharedPreferences.getInt(String.valueOf(currentPos),0);

            switch (choice) {
                case 1:
                    rb1.setChecked(true);
                    break;
                case 2:
                    rb2.setChecked(true);
                    break;
                case 3:
                    rb3.setChecked(true);
                    break;
                case 4:
                    rb4.setChecked(true);
                    break;
            }
        });
        bNext.setOnClickListener(view -> {
            bBack.setClickable(true);
            if(currentPos < questionList.size() - 1){
                bNext.setClickable(true);
                currentPos++;
                setDatatoView(currentPos);
            }else{
                bNext.setClickable(false);
            }
            rg.clearCheck();
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(true);
                rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            questionAttemped++;

            Log.i("current pos bNext", String.valueOf(currentPos));
            int choice = sharedPreferences.getInt(String.valueOf(currentPos),0);

            switch (choice) {
                case 1:
                    rb1.setChecked(true);
                    break;
                case 2:
                    rb2.setChecked(true);
                    break;
                case 3:
                    rb3.setChecked(true);
                    break;
                case 4:
                    rb4.setChecked(true);
                    break;
            }
        });
        bBTM.setOnClickListener(view -> {
            for(int i = 0; i < questionList.size(); i++){
                if(questionList.get(i).isLearned()){
                    databaseHelper.updateLearned(questionList.get(i).getId(),1);
                }else{
                    databaseHelper.updateLearned(questionList.get(i).getId(),0);
                }
                if(questionList.get(i).isSaved()){
                    databaseHelper.updateSaved(questionList.get(i).getId(),1);
                }else{
                    databaseHelper.updateSaved(questionList.get(i).getId(),0);
                }
            }
            Intent intent = new Intent(SavedActivity.this, MainActivity.class);
            startActivity(intent);
        });
        bSave.setOnClickListener(view -> {
            if(questionList.get(currentPos).isSaved()==true) {
                bSave.setBackgroundColor(Color.WHITE);
                questionList.get(currentPos).setSaved(false);
            }else{
                bSave.setBackgroundColor(Color.YELLOW);
                questionList.get(currentPos).setSaved(true);
            }
        });
    }
    private void setDatatoView(int currentPos){
        if(questionList.get(currentPos).isSaved()==true){
            bSave.setBackgroundColor(Color.YELLOW);
        }else{
            bSave.setBackgroundColor(Color.WHITE);
        }
        if(questionList.get(currentPos).isLearned()==true) {
            questionNum.setText("Câu " + (currentPos + 1) + "/" + questionList.size() +": đã học");
        }else {
            questionNum.setText("Câu "+(currentPos+1)+"/"+questionList.size()+":");
        }

        question.setText(questionList.get(currentPos).getTitle());
        if(questionList.get(currentPos).getImage()!=null) {
            String image = "@drawable/" + questionList.get(currentPos).getImage();
            int imageResource = getResources().getIdentifier(image.substring(0,image.indexOf(".")), null, getPackageName());
            question.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, imageResource);
        }else{
            question.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
        }
        rb1.setText("1."+questionList.get(currentPos).getOption1());
        rb2.setText("2."+questionList.get(currentPos).getOption2());
        rb3.setText("3."+questionList.get(currentPos).getOption3());
        rb4.setText("4."+questionList.get(currentPos).getOption4());
        if(questionList.get(currentPos).getOption3()==null)
            rb3.setVisibility(View.INVISIBLE);
        else
            rb3.setVisibility(View.VISIBLE);
        if(questionList.get(currentPos).getOption4()==null)
            rb4.setVisibility(View.INVISIBLE);
        else
            rb4.setVisibility(View.VISIBLE);
    }
}