package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidfinal.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    CardView cvDiemLiet,cvTest, cvKhaiNiem, cvVanHoa, cvKyThuat, cvCauTao, cvBienBao, cvSaHinh, cvIsSaved, cvSign;
    TextView tvisSaved, tvDLcount, tvBBcount, tvKNcount, tvVHcount, tvKTcount, tvCTcount, tvSHcount;
    ProgressBar progressBarKN, progressBarDL, progressBarBB, progressBarVH, progressBarKT, progressBarCT, progressBarSH;
    ImageButton buttonTest, buttonSaved, buttonSign;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        function();
    }
    private void init(){
        tvBBcount = findViewById(R.id.tv_BB_count);
        tvDLcount = findViewById(R.id.tv_DL_count);
        tvKNcount = findViewById(R.id.tv_KN_count);
        tvVHcount = findViewById(R.id.tv_VH_count);
        tvKTcount = findViewById(R.id.tv_KT_count);
        tvCTcount = findViewById(R.id.tv_CT_count);
        tvSHcount = findViewById(R.id.tv_SH_count);
        progressBarKN = findViewById(R.id.progressBarKN);
        progressBarBB = findViewById(R.id.progressBarBB);
        progressBarVH = findViewById(R.id.progressBarVH);
        progressBarKT = findViewById(R.id.progressBarKT);
        progressBarCT = findViewById(R.id.progressBarCT);
        progressBarSH = findViewById(R.id.progressBarSH);
        progressBarDL = findViewById(R.id.progressBarDL);
        tvisSaved = findViewById(R.id.tv_isSaved);
        cvDiemLiet =findViewById(R.id.cv_diemliet);
        cvBienBao = findViewById(R.id.cv_bbvdb);
        cvKhaiNiem = findViewById(R.id.cv_knvqt);
        cvVanHoa = findViewById(R.id.cv_vhlx);
        cvKyThuat = findViewById(R.id.cv_ktlx);
        cvCauTao = findViewById(R.id.cv_ctvsc);
        cvSaHinh = findViewById(R.id.cv_sahinh);
        cvIsSaved = findViewById(R.id.cv_isSaved);
        cvSign =findViewById(R.id.cv_sign);
        cvTest = findViewById(R.id.cv_test);
        buttonTest = findViewById(R.id.image_button_test);
        buttonSaved = findViewById(R.id.image_button_saved);
        buttonSign = findViewById(R.id.image_button_sign);
        databaseHelper = new DatabaseHelper(this);
        int maxKN = databaseHelper.countQuestion("KNQTGT");
        int progressKN = databaseHelper.countDoneQuestion("KNQTGT");
        float KN = (float) progressKN/maxKN;
        progressBarKN.setProgress(Math.round(KN*100));
        progressBarKN.setMax(maxKN);
        progressBarBB.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("BB")/ databaseHelper.countQuestion("BB"))));
        progressBarBB.setMax(databaseHelper.countQuestion("BB"));
        progressBarDL.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("DL")/ databaseHelper.countQuestion("DL"))));
        progressBarDL.setMax(databaseHelper.countQuestion("DL"));

        tvisSaved.setText("Đã lưu(" + databaseHelper.countSavedQuestion()+")");
        tvBBcount.setText("Đã học " + databaseHelper.countDoneQuestion("BB") +"/"+databaseHelper.countQuestion("BB"));
        tvKNcount.setText("Đã học " + databaseHelper.countDoneQuestion("KN") +"/"+databaseHelper.countQuestion("KN"));
        tvDLcount.setText("Đã học " + databaseHelper.countDoneQuestion("DL") +"/"+databaseHelper.countQuestion("DL"));
        tvSHcount.setText("Đã học " + databaseHelper.countDoneQuestion("SH") +"/"+databaseHelper.countQuestion("SH"));
        tvCTcount.setText("Đã học " + databaseHelper.countDoneQuestion("CT") +"/"+databaseHelper.countQuestion("CT"));
        tvKTcount.setText("Đã học " + databaseHelper.countDoneQuestion("KT") +"/"+databaseHelper.countQuestion("KT"));
        tvVHcount.setText("Đã học " + databaseHelper.countDoneQuestion("VH") +"/"+databaseHelper.countQuestion("VH"));

    }
    private void function(){
        cvDiemLiet.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","diemliet");
            startActivity(intent);
        });
        cvSaHinh.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","sahinh");
            startActivity(intent);
        });
        cvCauTao.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","cautao");
            startActivity(intent);
        });
        cvKyThuat.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","kythuat");
            startActivity(intent);
        });
        cvBienBao.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","bienbao");
            startActivity(intent);
        });
        cvVanHoa.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","vanhoa");
            startActivity(intent);
        });
        cvKhaiNiem.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","khainiem");
            startActivity(intent);
        });
        cvTest.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
            startActivity(intent);
        });
        buttonTest.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
            startActivity(intent);
        });
        buttonSaved.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SavedActivity.class);
            startActivity(intent);
        });
        buttonSign.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignalActivity.class);
            startActivity(intent);
        });

    }
}