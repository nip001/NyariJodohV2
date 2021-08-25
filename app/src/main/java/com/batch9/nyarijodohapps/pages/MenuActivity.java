package com.batch9.nyarijodohapps.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.batch9.nyarijodohapps.R;
import com.batch9.nyarijodohapps.entity.UserEntity;
import com.batch9.nyarijodohapps.utility.JWTUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class MenuActivity extends AppCompatActivity {

    Button btnDataCalon,btnPilihCalon,btnKeluar;
    TextView txtHello;
    UserEntity userTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnDataCalon = findViewById(R.id.btnDataCalon);
        btnPilihCalon = findViewById(R.id.btnPilihCalon);
        btnKeluar = findViewById(R.id.btnKeluar);
        txtHello = findViewById(R.id.txtHello);
        String token = getIntent().getStringExtra("jwttoken");

        Gson gson = new Gson();
        try {
            userTemp = gson.fromJson(JWTUtil.getBodyDecode(token), UserEntity.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        txtHello.setText("Hello "+userTemp.getName());

        btnPilihCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this,TinderActivity.class);
                i.putExtra("jwttoken",token);
                startActivity(i);
            }
        });
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}