package com.example.registerloginexample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Btn_AddSite =  findViewById(R.id.Btn_AddSite);
        Btn_AddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SiteAddActivity.class);
                startActivity(intent);
            }

        });
    }
    //뒤로가기 두번 누르면 종료

    private long lastTimeBackPressed;


    @Override

    public void onBackPressed(){
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){ //뒤로가기 한번 누르고 1.5초안에 한번 더 누르면 종료
            finish();
            return;
        }
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 눌러 종료합니다.",Toast.LENGTH_SHORT);
        lastTimeBackPressed=System.currentTimeMillis();
    }

}


