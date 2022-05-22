package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidfinal.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    CardView cvDiemLiet,cvTest, cvKhaiNiem, cvVanHoa, cvKyThuat, cvCauTao, cvBienBao, cvSaHinh, cvIsSaved, cvSign, cvNghiepVu, cvSetting;
    TextView tvisSaved, tvDLcount, tvBBcount, tvKNcount, tvVHcount, tvKTcount, tvCTcount, tvSHcount, tvNVcount, tvType;
    ProgressBar progressBarKN, progressBarDL, progressBarBB, progressBarVH, progressBarKT, progressBarCT, progressBarSH, progressBarNV;
    ImageButton buttonTest, buttonSaved, buttonSign, buttonSetting;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
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
        tvNVcount = findViewById(R.id.tv_NV_count);
        tvType = findViewById(R.id.tv_type);
        progressBarKN = findViewById(R.id.progressBarKN);
        progressBarBB = findViewById(R.id.progressBarBB);
        progressBarVH = findViewById(R.id.progressBarVH);
        progressBarKT = findViewById(R.id.progressBarKT);
        progressBarCT = findViewById(R.id.progressBarCT);
        progressBarSH = findViewById(R.id.progressBarSH);
        progressBarDL = findViewById(R.id.progressBarDL);
        progressBarNV = findViewById(R.id.progressBarNV);
        tvisSaved = findViewById(R.id.tv_isSaved);
        cvNghiepVu = findViewById(R.id.cv_nghiepvu);
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
        cvSetting = findViewById(R.id.cv_setting);
        buttonTest = findViewById(R.id.image_button_test);
        buttonSaved = findViewById(R.id.image_button_saved);
        buttonSign = findViewById(R.id.image_button_sign);
        databaseHelper = new DatabaseHelper(this);
        int maxKN = databaseHelper.countQuestion("KN");
        int progressKN = databaseHelper.countDoneQuestion("KN");
        float KN = (float) progressKN/maxKN;
        progressBarKN.setProgress(Math.round(KN*100));
        progressBarKN.setMax(100);
        progressBarBB.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("BB")/ databaseHelper.countQuestion("BB"))));
        progressBarBB.setMax(100);
        progressBarDL.setProgress(Math.round(100*((float)databaseHelper.countDoneEssQuestion()/ databaseHelper.countEssQuestion())));
        progressBarDL.setMax(100);
        progressBarSH.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("SH")/ databaseHelper.countQuestion("SH"))));
        progressBarSH.setMax(100);
        progressBarCT.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("CT")/ databaseHelper.countQuestion("CT"))));
        Log.i("prog",String.valueOf(Math.round(100*((float)databaseHelper.countDoneQuestion("CT")/ databaseHelper.countQuestion("CT")))));
        progressBarCT.setMax(100);
        progressBarVH.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("VH")/ databaseHelper.countQuestion("VH"))));
        progressBarVH.setMax(100);
        progressBarKT.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("KT")/ databaseHelper.countQuestion("KT"))));
        progressBarKT.setMax(100);
        progressBarNV.setProgress(Math.round(100*((float)databaseHelper.countDoneQuestion("NV")/ databaseHelper.countQuestion("NV"))));
        progressBarNV.setMax(100);

        tvisSaved.setText("Đã lưu(" + databaseHelper.countSavedQuestion()+")");
        tvBBcount.setText("Đã học " + databaseHelper.countDoneQuestion("BB") +"/"+databaseHelper.countQuestion("BB"));
        tvKNcount.setText("Đã học " + databaseHelper.countDoneQuestion("KN") +"/"+databaseHelper.countQuestion("KN"));
        tvDLcount.setText("Đã học " + databaseHelper.countDoneEssQuestion() +"/"+databaseHelper.countEssQuestion());
        tvSHcount.setText("Đã học " + databaseHelper.countDoneQuestion("SH") +"/"+databaseHelper.countQuestion("SH"));
        tvCTcount.setText("Đã học " + databaseHelper.countDoneQuestion("CT") +"/"+databaseHelper.countQuestion("CT"));
        tvKTcount.setText("Đã học " + databaseHelper.countDoneQuestion("KT") +"/"+databaseHelper.countQuestion("KT"));
        tvVHcount.setText("Đã học " + databaseHelper.countDoneQuestion("VH") +"/"+databaseHelper.countQuestion("VH"));
        tvNVcount.setText("Đã học " + databaseHelper.countDoneQuestion("NV") +"/"+databaseHelper.countQuestion("NV"));
        sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String type = sharedPreferences.getString("type","B1");
        if(type.equals("B1")){
            cvNghiepVu.setVisibility(View.INVISIBLE);
            tvType.setText("Loại bằng: B1");
        }else{
            cvNghiepVu.setVisibility(View.VISIBLE);
            tvType.setText("Loại bằng: B2");
        }
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
        cvNghiepVu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("type","nghiepvu");
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
        cvSetting.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }
}