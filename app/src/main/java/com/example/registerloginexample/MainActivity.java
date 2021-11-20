package com.example.registerloginexample;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private ListView PlusSitedListView;
    private PlusSiteListAdapter adapter;
    private List<PlusSite> PlusSitedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //7강때 추가된 부분
        PlusSitedListView = (ListView)findViewById(R.id.PlusSitedListView);
        PlusSitedList = new ArrayList<PlusSite>();

        adapter = new PlusSiteListAdapter(getApplicationContext(), PlusSitedList);
        PlusSitedListView.setAdapter(adapter);


        Button Btn_AddSite =  findViewById(R.id.Btn_AddSite);
        Btn_AddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SiteAddActivity.class);
                startActivity(intent);
            }

        });
    }
    //AsyncTask 실행 부분
        new void BackgroundTask().execute();

    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    class BackgroundTask extends AsyncTask<Void, Void, String>
       String target;
    @Override
    protected void onPreExecute()
            super.onPreExecute();
    target = "http://ynns1217.ivyro.net/NoticeList.php";

    //실제 데이터를 가져오는 부분임
    @Override
    protected String doInBackground(Void... voids)
            try
    URL url = new URL(target);
    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
    InputStream inputStream = httpURLConnection.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    String temp;//결과 값을 여기에 저장함
    StringBuilder stringBuilder = new StringBuilder();
    //버퍼생성후 한줄씩 가져옴
                while((temp = bufferedReader.readLine()) != null)
            stringBuilder.append(temp + "");

               bufferedReader.close();
               inputStream.close();
               httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//결과값이 여기에 리턴되면 이 값이 onPostExcute의 파라미터로 넘어감
            catch(Exception e)
            e.printStackTrace();

            return null;

    @Override
    protected void onProgressUpdate(Void... values)
            super.onProgressUpdate(values);

    //여기서는 가져온 데이터를 Notice객체에 넣은뒤 리스트뷰 출력을 위한 List객체에 넣어주는 부분
    @Override
    protected void onPostExecute(String result)
            super.onPostExecute(result);
            try
    JSONObject jsonObject = new JSONObject(result);
    JSONArray jsonArray = jsonObject.getJSONArray("response");
    int count = 0;
    String noticeContent, noticeName, noticeDate;
    //json타입의 값을 하나씩 빼서 Notice 객체에 저장후 리스트에 추가하는 부분
                while(count <jsonArray.length())
    JSONObject object = jsonArray.getJSONObject(count);
    noticeContent = object.getString("noticeContent");
    noticeName = object.getString("noticeName");
    noticeDate = object.getString("noticeDate");
    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticedList.add(notice);
    count++;

           catch (Exception e)
            e.printStackTrace();


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


