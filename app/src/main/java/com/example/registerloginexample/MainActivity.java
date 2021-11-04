package com.example.registerloginexample;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button DomainSearchBtn = (Button) findViewById(R.id.DomainSearchBtn);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);


        DomainSearchBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);//공지사항이 안보임
                DomainSearchBtn.setBackgroundColor(getResources().getColor(R.color.black));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new DomainFragment());//프래그먼트를 바꿔줌
                fragmentTransaction.commit();
            }


        });

    }
}