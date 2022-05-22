package com.example.androidfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfinal.adapter.ExamGridViewAdapter;
import com.example.androidfinal.database.DatabaseHelper;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExamActivity extends AppCompatActivity {
    private GridView gridView;
    private ImageButton buttonExit, buttonNext, buttonBack;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button buttonSubmit;
    private ArrayList<Question> questionList, vhList, shList, ctList, ktList, knList, bbList, nvList, dlList;
    private RadioGroup rg;
    private ExamGridViewAdapter gridViewAdapter;
    SharedPreferences sharedPreferences, spType;
    SharedPreferences.Editor editor;
    private TextView textViewTimer, textViewQuestionNum, textViewQuestion;
    private CountDownTimer countDownTimer;
    private DatabaseHelper databaseHelper;
    private int currentPos;
    private int[] index={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
    private int[] index1={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};

    private boolean[] isDone = new boolean[35];
    private long duration = TimeUnit.MINUTES.toMillis(22);
    private int[] doneQuestion= new int[35];
    private int[] essQuestion = new int[35];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        init();
        function();
    }
    private void init(){
        questionList = new ArrayList<>();
        gridView = findViewById(R.id.grid_view);
        gridViewAdapter = new ExamGridViewAdapter(this,index,isDone);
        gridView.setAdapter(gridViewAdapter);
        buttonExit = findViewById(R.id.button_exit_exam);
        buttonSubmit = findViewById(R.id.button_submit);
        textViewTimer = findViewById(R.id.tv_timer);
        textViewQuestionNum = findViewById(R.id.tv_question_id_exam);
        textViewQuestion = findViewById(R.id.tv_question_exam);
        currentPos = 0;
        buttonBack = findViewById(R.id.button_back_exam);
        buttonNext = findViewById(R.id.button_next_exam);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        rg = findViewById(R.id.radioGroup);
        sharedPreferences = getSharedPreferences("exam", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        spType = getSharedPreferences("type",MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);
        vhList = databaseHelper.getQuestionByType("VH");
        shList = databaseHelper.getQuestionByType("SH");
        ctList = databaseHelper.getQuestionByType("CT");
        ktList = databaseHelper.getQuestionByType("KT");
        knList = databaseHelper.getQuestionByType("KN");
        bbList = databaseHelper.getQuestionByType("BB");
        nvList = databaseHelper.getQuestionByType("NV");
        dlList = databaseHelper.getEssQuestion();
        if(spType.getString("type",null).equals("B1")){
            getRandomQuestion(vhList,1);
            getRandomQuestion(shList,9);
            getRandomQuestion(ctList,1);
            getRandomQuestion(bbList,9);
            getRandomQuestion(knList,8);
            getRandomQuestion(ktList,1);
            getRandomQuestion(dlList,1);
            duration = TimeUnit.MINUTES.toMillis(20);

            isDone = new boolean[30];
            doneQuestion= new int[30];
            essQuestion = new int[30];
            gridViewAdapter = new ExamGridViewAdapter(this,index1,isDone);
            gridView.setAdapter(gridViewAdapter);
        }else{
            getRandomQuestion(vhList,1);
            getRandomQuestion(shList,10);
            getRandomQuestion(ctList,1);
            getRandomQuestion(bbList,10);
            getRandomQuestion(knList,9);
            getRandomQuestion(ktList,2);
            getRandomQuestion(dlList,1);
            getRandomQuestion(nvList,1);
        }
        //getQuizQuestion(questionList);
        setArrTempID(questionList);
        setDatatoView(currentPos);

        Log.i("child count", String.valueOf(gridView.getChildCount()));
        gridView.setVerticalScrollBarEnabled(false);


    }
    private void function(){
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                textViewTimer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
                intent.putExtra("essQuestion",essQuestion);
                intent.putExtra("doneQuestion",doneQuestion);
                intent.putExtra("questionList",questionList);
                startActivity(intent);
            }
        }.start();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialog = new AlertDialog.Builder(ExamActivity.this)
                        .setTitle("Nộp bài")
                        .setMessage("Bạn có muốn nộp bài?")
                        .setPositiveButton("Có", null)
                        .setNegativeButton("Không", null)
                        .show();
                Button posButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                posButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countDownTimer.onFinish();
                    }
                });
                Button negButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
            }
        });
        buttonExit.setOnClickListener(view -> {
            final AlertDialog dialog = new AlertDialog.Builder(ExamActivity.this)
                    .setTitle("Thoát bài thi")
                    .setMessage("Bạn có chắc muốn thoát khỏi bài thi?")
                    .setPositiveButton("Có", null)
                    .setNegativeButton("Không", null)
                    .show();
            Button posButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            posButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countDownTimer.onFinish();
                }
            });
            Button negButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            negButton.setOnClickListener(view1 -> dialog.dismiss());
        });
        rb1.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb1.getText().toString().substring(0,1))){
                questionList.get(currentPos).setLearned(true);

                editor.putInt(String.valueOf(currentPos),1).apply();
                doneQuestion[currentPos] = 1;
                isDone[currentPos]=true;
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 0;
            }
            else{
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 1;
                doneQuestion[currentPos] = 2;
                isDone[currentPos]=true;
            }

        });
        rb2.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb2.getText().toString().substring(0,1))){
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),2).apply();
                doneQuestion[currentPos] = 1;
                isDone[currentPos]=true;
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 0;
            }
            else{
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 1;
                doneQuestion[currentPos] = 2;
                isDone[currentPos]=true;
            }

        });
        rb3.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb3.getText().toString().substring(0,1))){
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),3).apply();
                doneQuestion[currentPos] = 1;
                isDone[currentPos]=true;
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 0;
            }
            else{
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 1;
                doneQuestion[currentPos] = 2;
                isDone[currentPos]=true;
            }
        });
        rb4.setOnClickListener(view -> {
            if(questionList.get(currentPos).getRightAnswer()==Integer.parseInt(rb4.getText().toString().substring(0,1))){
                questionList.get(currentPos).setLearned(true);
                editor.putInt(String.valueOf(currentPos),4).apply();
                doneQuestion[currentPos] = 1;
                isDone[currentPos]=true;
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 0;
            }
            else{
                if(questionList.get(currentPos).isEssential()==true)
                    essQuestion[currentPos] = 1;
                doneQuestion[currentPos] = 2;
                isDone[currentPos]=true;
            }
        });
        buttonBack.setOnClickListener(view -> {

            buttonNext.setClickable(true);
            if(currentPos > 0){
                buttonBack.setClickable(true);
                currentPos--;
                setDatatoView(currentPos);
            }else{
                buttonBack.setClickable(false);
            }
            rg.clearCheck();
            for (int i = 0; i < rg.getChildCount(); i++) {
                rg.getChildAt(i).setClickable(true);
                rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            rg.setBackgroundColor(Color.WHITE);

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
        buttonNext.setOnClickListener(view -> {
            Log.i("tag",String.valueOf(doneQuestion[currentPos]));
//            if(rg.getCheckedRadioButtonId()==-1){
//                doneQuestion[currentPos]=2;
//            }
            buttonBack.setClickable(true);
            if(currentPos < questionList.size() - 1){
                buttonNext.setClickable(true);
                currentPos++;
                setDatatoView(currentPos);
            }else{
                buttonNext.setClickable(false);
            }
            rg.clearCheck();
            for(int i = 0; i < rg.getChildCount(); i++){
                rg.getChildAt(i).setClickable(true);
                rg.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
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
        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            currentPos=i;
            setDatatoView(i);
            Log.i("i",String.valueOf(i));
        });
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == MotionEvent.ACTION_MOVE;
            }
        });

    }
    private void setDatatoView(int currentPos){
        gridViewAdapter.notifyDataSetChanged();
//        gridViewAdapter = new ExamGridViewAdapter(this,index){
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
//                View result = super.getView(i,view,viewGroup);
//                TextView textView = result.findViewById(R.id.tv_id_grid);
//                if(questionList.get(i).isLearned()==true)
//                    textView.setBackgroundColor(Color.YELLOW);
//                textView.setText(String.valueOf(index[i]));
//                return result;
//            }
//        };
        //gridView.setAdapter(gridViewAdapter);

        textViewQuestionNum.setText("Câu "+(currentPos+1)+"/"+questionList.size()+":");
        textViewQuestion.setText(questionList.get(currentPos).getTitle());
        if(questionList.get(currentPos).getImage()!=null) {
            String image = "@drawable/" + questionList.get(currentPos).getImage();
            Log.i("image",image);
            int imageResource = getResources().getIdentifier(image.substring(0,image.indexOf(".")), null, getPackageName());
            textViewQuestion.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, imageResource);
        }else{
            textViewQuestion.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
        }
        rb1.setText(questionList.get(currentPos).getOption1());
        rb2.setText(questionList.get(currentPos).getOption2());
        rb3.setText(questionList.get(currentPos).getOption3());
        rb4.setText(questionList.get(currentPos).getOption4());
        if(questionList.get(currentPos).getOption3()==null)
            rb3.setVisibility(View.INVISIBLE);
        else
            rb3.setVisibility(View.VISIBLE);
        if(questionList.get(currentPos).getOption4()==null)
            rb4.setVisibility(View.INVISIBLE);
        else
            rb4.setVisibility(View.VISIBLE);
    }
    private void setArrTempID(ArrayList<Question> list){
        for (int i = 0; i < list.size(); i++)
            list.get(i).setTempID(i+1);
    }
    private void getRandomQuestion(ArrayList<Question> list, int quantity){
        Random random = new Random();
        for (int i = 0; i < quantity; i++){
            questionList.add(list.get(random.nextInt(list.size())));
        }
    }
    private void getQuizQuestion(ArrayList<Question> questionList){
        questionList.add(new Question("Test1","1.Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ","2.Wrong","3.Wrong","4.True",1));
        questionList.get(0).setEssential(true);
        questionList.get(0).setImage("exam1.png");
        questionList.get(0).setOption3(null);
        questionList.get(0).setOption4(null);
        questionList.add(new Question("Test2","1.Wrong","2.True","3.Wrong","4.True",2));
        questionList.add(new Question("Test3","1.Wrong","2.Wrong","3.True","4.True",3));
        questionList.add(new Question("Test4","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test5","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test6","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.get(5).setEssential(true);
        questionList.add(new Question("Test7","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test8","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test9","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test10","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test1","1.Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ","2.Wrong","3.Wrong","4.True",1));
        questionList.add(new Question("Test2","1.Wrong","2.True","3.Wrong","4.True",2));
        questionList.add(new Question("Test3","1.Wrong","2.Wrong","3.True","4.True",3));
        questionList.add(new Question("Test4","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test5","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test6","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test7","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test8","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test9","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test10","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test1","1.Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ","2.Wrong","3.Wrong","4.True",1));
        questionList.add(new Question("Test2","1.Wrong","2.True","3.Wrong","4.True",2));
        questionList.add(new Question("Test3","1.Wrong","2.Wrong","3.True","4.True",3));
        questionList.add(new Question("Test4","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test5","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test6","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test7","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test8","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test9","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test10","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test6","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test7","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test8","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test9","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.add(new Question("Test10","1.Wrong","2.Wrong","3.Wrong","4.True",4));
        questionList.get(20).setImage("car.png");
    }
}