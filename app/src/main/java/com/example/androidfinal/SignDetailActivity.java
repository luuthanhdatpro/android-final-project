package com.example.androidfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidfinal.model.Sign;

import java.io.Serializable;

public class SignDetailActivity extends AppCompatActivity {
    ImageView imageView;
    ImageButton buttonBack;
    TextView tvTitle, tvName, tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);
        imageView = findViewById(R.id.image_sign_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvName = findViewById(R.id.tv_name_detail);
        tvDescription = findViewById(R.id.tv_description_detail);
        buttonBack = findViewById(R.id.button_back_detail);
        buttonBack.setOnClickListener(view -> {
            Intent intent = new Intent(SignDetailActivity.this, SignalActivity.class);
            startActivity(intent);
        });
        Intent intent = this.getIntent();
        Sign sign =(Sign) intent.getSerializableExtra("sign");
        String image = "@drawable/" + sign.getImage();
        int imageResource = getResources().getIdentifier(image.substring(0,image.indexOf(".")), null, getPackageName());
        imageView.setImageResource(imageResource);
        tvTitle.setText(sign.getTitle());
        tvName.setText(sign.getName());
        tvDescription.setText(sign.getMeaning());
    }
}