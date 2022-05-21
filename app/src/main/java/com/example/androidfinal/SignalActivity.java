package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidfinal.adapter.ExamListAdapter;
import com.example.androidfinal.adapter.SignListAdapter;
import com.example.androidfinal.database.DatabaseHelper;
import com.example.androidfinal.inteface.ExamListOnClickListener;
import com.example.androidfinal.inteface.SignListOnClickListener;
import com.example.androidfinal.model.Exam;
import com.example.androidfinal.model.Sign;

import java.util.ArrayList;

public class SignalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SignListAdapter signListAdapter;
    private ImageButton buttonBack;
    private CardView cvBBCam, cvBBNguyHiem, cvBBHieuLenh, cvBBChiDan, cvBBPhu;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal);
        init();
        function();
}
    private void init(){
        databaseHelper = new DatabaseHelper(this);
        buttonBack = findViewById(R.id.button_back_sign);
        cvBBCam = findViewById(R.id.cv_bienbaocam);
        cvBBNguyHiem = findViewById(R.id.cv_bbnguyhiem);
        cvBBHieuLenh = findViewById(R.id.cv_bbhieulenh);
        cvBBChiDan = findViewById(R.id.cv_bbchidan);
        cvBBPhu = findViewById(R.id.cv_bbphu);
        recyclerView = findViewById(R.id.recycle_sign);
        buttonBack.setOnClickListener(view -> {
            Intent intent = new Intent(SignalActivity.this, MainActivity.class);
            startActivity(intent);
        });
        signListAdapter = new SignListAdapter(getSignList(), new SignListOnClickListener() {
            @Override
            public void clickItem(Sign sign) {
                Intent intent = new Intent(SignalActivity.this, SignDetailActivity.class);
                intent.putExtra("name",sign.getName());
                intent.putExtra("tile",sign.getTitle());
                intent.putExtra("meaning",sign.getMeaning());
                intent.putExtra("image",sign.getImage());
                intent.putExtra("sign",sign);
                startActivity(intent);
            }
        }, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        signListAdapter.setData(getSignList());
        recyclerView.setAdapter(signListAdapter);

    }
    private void function(){
        cvBBCam.setOnClickListener(view -> signListAdapter.setData(databaseHelper.getSignByType("cam")));
        cvBBCam.setOnClickListener(view -> signListAdapter.setData(databaseHelper.getSignByType("nguyhiem")));
        cvBBCam.setOnClickListener(view -> signListAdapter.setData(databaseHelper.getSignByType("hieulenh")));
        cvBBCam.setOnClickListener(view -> signListAdapter.setData(databaseHelper.getSignByType("chidan")));
        cvBBCam.setOnClickListener(view -> signListAdapter.setData(databaseHelper.getSignByType("phu")));
    }
    private ArrayList<Sign> getSignList(){
        ArrayList<Sign> signs = new ArrayList<>();
        signs.add(new Sign(1,"P101","Cam gi do","bien bao de cam di lai cac thu cac thu cac thu","signp101.png","cam"));
        signs.add(new Sign(2,"P101","Cam gi do","bien bao de cam di lai cac thu cac thu cac thu","signp101.png","cam"));
        signs.add(new Sign(3,"P101","Cam gi do","bien bao de cam di lai cac thu cac thu cac thu","signp101.png","cam"));
        signs.add(new Sign(4,"P101","Cam gi do","bien bao de cam di lai cac thu cac thu cac thu","signp101.png","cam"));
        signs.add(new Sign(5,"P101","Cam gi do","bien bao de cam di lai cac thu cac thu cac thu","signp101.png","cam"));
        return signs;
    }
}