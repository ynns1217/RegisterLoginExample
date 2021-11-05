package com.example.registerloginexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userID=getIntent().getStringExtra("userID");


        final Button courseButton=(Button) findViewById(R.id.DomainSearchBtn);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new DialogFragment());
                fragmentTransaction.commit();
            }
        });



        new BackgroundTask().execute();
    }



    //사이트 리스트
    class BackgroundTask extends AsyncTask<Void, Void, String >
    {
        String target;

        @Override
        protected void onPreExecute(){
            //super.onPreExecute();
            target="https://ynns1217.ivyro.net/CourseList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url=new URL(target);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while((temp=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }



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


