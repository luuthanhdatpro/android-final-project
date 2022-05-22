package com.example.androidfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.androidfinal.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    Spinner spinner;
    CardView cardView;
    ImageButton imageButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        function();
    }
    private void function(){
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putString("type",adapterView.getItemAtPosition(i).toString()).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cardView.setOnClickListener(view -> {
            final AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this)
                    .setTitle("Xóa dữ liệu")
                    .setMessage("Bạn có chắc muốn xóa hết dữ liệu?")
                    .setPositiveButton("Có", null)
                    .setNegativeButton("Không", null)
                    .show();
            Button posButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            posButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseHelper.deleteAll();
                    Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            Button negButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            negButton.setOnClickListener(view1 -> dialog.dismiss());
        });
    }
    private void init(){
        spinner = findViewById(R.id.spinner);
        cardView = findViewById(R.id.cv_delete);
        imageButton = findViewById(R.id.button_back_setting);
        ArrayList<String> type = new ArrayList<>();
        type.add("B1");
        type.add("B2");
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);
        sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        databaseHelper = new DatabaseHelper(this);
        if(sharedPreferences.getString("type",null).equals("B1")){
            spinner.setSelection(0);
        }else{
            spinner.setSelection(1);
        }
    }
}