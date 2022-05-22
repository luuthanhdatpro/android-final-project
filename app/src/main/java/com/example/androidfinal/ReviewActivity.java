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
import com.example.androidfinal.fragment.BottomSheetFragment;
import com.example.androidfinal.inteface.BottomSheetOnclickListener;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;
import java.util.Random;

public class ReviewActivity extends AppCompatActivity {
    private TextView question, questionNum;
    private RadioButton rb1, rb2, rb3, rb4;
    private ArrayList<Question> questionList;
    private ImageButton bNext,bBack, bBTM, bSave, bBTS;
    private RadioGroup rg;
    Random random;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int questionAttemped = 1, currentPos;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        init();
        databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.checkDB();
        }catch (Exception e){
            e.printStackTrace();
        }
//        try {
//            databaseHelper.openDatabase();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Intent intent = this.getIntent();
        switch (intent.getStringExtra("type")){
            case "vanhoa":
                questionList = databaseHelper.getQuestionByType("VH");
                break;
            case "diemliet":
                questionList = databaseHelper.getEssQuestion();
                break;
            case "sahinh":
                questionList = databaseHelper.getQuestionByType("SH");
                break;
            case "cautao":
                questionList = databaseHelper.getQuestionByType("CT");
                break;
            case "kythuat":
                questionList = databaseHelper.getQuestionByType("KT");
                break;
            case "khainiem":
                questionList = databaseHelper.getQuestionByType("KN");
                break;
            case "bienbao":
                questionList = databaseHelper.getQuestionByType("BB");
                break;
            case "nghiepvu":
                questionList = databaseHelper.getQuestionByType("NV");
                break;
            default:
                questionList = databaseHelper.getQuestionByType("KNQTGT");
                break;

        }

        //getQuizQuestion(questionList);

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
        bSave = findViewById(R.id.button_save);
        bBTS = findViewById(R.id.button_open_bts);
        questionList = new ArrayList<>();
        random = new Random();
    }
    private void function(){
        rb1.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb1.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb1.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),1).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(ReviewActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb1.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb2.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb2.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb2.setBackgroundColor(Color.parseColor("#00FF19"));
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),2).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(ReviewActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb2.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb3.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb3.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb3.setBackgroundColor(Color.GREEN);
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),3).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(ReviewActivity.this,"Sai",Toast.LENGTH_SHORT).show();
                rb3.setBackgroundColor(Color.RED);
            }
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(false);
            }
        });
        rb4.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb4.getText().toString().substring(0,1))){
                Toast.makeText(ReviewActivity.this,"Đúng",Toast.LENGTH_SHORT).show();
                rb4.setBackgroundColor(Color.GREEN);
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),4).apply();
            }
            else{
                questionList.get(currentPos).setLearned(false);
                Toast.makeText(ReviewActivity.this,"Sai",Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
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
        bBTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                clickOpenBottomSheet();
            }
        });

    }
    private void clickOpenBottomSheet() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(questionList, this);
        bottomSheetFragment.setOnclickListener(question -> {
            setDatatoView(question.getTempID()-1);
            currentPos = question.getTempID();
            bottomSheetFragment.dismiss();
        });

        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
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
    private void getQuizQuestion(ArrayList<Question> questionList){

//        questionList.add(new Question("Test1","1.Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ","2.Wrong","3.Wrong","4.True",1));
//        questionList.get(0).setEssential(true);
//        questionList.get(0).setOption3(null);
//        questionList.get(0).setOption4(null);
//        questionList.add(new Question("Test2","1.Wrong","2.True","3.Wrong","4.True",2));
//        questionList.add(new Question("Test3","1.Wrong","2.Wrong","3.True","4.True",3));
//        questionList.add(new Question("Test4","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test5","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test6","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.get(5).setEssential(true);
//        questionList.add(new Question("Test7","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test8","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test9","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test10","1.Wrong","2.Wrong","3.Wrong","4.True",4));
//        questionList.add(new Question("Test11","1.Wrong","2.Wrong","3.Wrong","4.True",4));

    }
}