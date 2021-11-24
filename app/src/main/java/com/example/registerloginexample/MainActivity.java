package com.example.registerloginexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
    private List<PlusSiteResult> sitedList = new ArrayList<PlusSiteResult>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView plusSitedListView = (ListView) findViewById(R.id.PlusSitedListView);
        sitedList = new ArrayList<PlusSiteResult>();

        PlusSiteListAdapter adapter = new PlusSiteListAdapter(getApplicationContext(), sitedList);
        plusSitedListView.setAdapter(adapter);
        final LinearLayout site = (LinearLayout)findViewById(R.id.site);

        Button Btn_AddSite = findViewById(R.id.Btn_AddSite);
        Btn_AddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SiteAddActivity.class);
                startActivity(intent);
            }

        });
        new BackgroundTask().execute();
    }


    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            target = "http://ynns1217.ivyro.net/plusSiteResultList.php";
        }

        //실제 데이터를 가져오는 부분임
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;//결과 값을 여기에 저장함
                StringBuilder stringBuilder = new StringBuilder();
                //버퍼생성후 한줄씩 가져옴
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp+"");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//결과값이 여기에 리턴되면 이 값이 onPostExcute의 파라미터로 넘어감
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //여기서는 가져온 데이터를 site객체에 넣은뒤 리스트뷰 출력을 위한 List객체에 넣어주는 부분
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String plusSite, plusID, plusPassword;
                //json타입의 값을 하나씩 빼서 list 객체에 저장후 리스트에 추가하는 부분

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    plusSite = object.getString("plusSite");
                    plusID = object.getString("plusID");
                    plusPassword = object.getString("plusPassword");
                    PlusSiteResult site = new PlusSiteResult(plusSite, plusID, plusPassword);
                    sitedList.add(site);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //뒤로가기 두번 누르면 종료
}