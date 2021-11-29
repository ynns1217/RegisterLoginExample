
package com.example.registerloginexample;

        import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class  SiteAddActivity extends AppCompatActivity {

    private EditText plus_site, plus_id, plus_pass, plus_pass2;
    private Button btn_site;
    private Button btn_back;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티 시작시 청므으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteplus);

        //아이디 값 찾아주기
        plus_id = findViewById(R.id.plus_id);
        plus_pass = findViewById(R.id.plus_pass);
        plus_pass2 = findViewById(R.id.plus_pass2);
        plus_site = findViewById(R.id.plus_site);

        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


        //사이트 중복확인 버튼 클릭 시 수행
        btn_site = findViewById(R.id.btn_site);
        btn_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plusSite = plus_site.getText().toString();
                if (validate) {
                    return;
                }
                if (plusSite.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteAddActivity.this);
                    dialog = builder.setMessage("사이트 명은 빈 칸일 수 없습니다") //정상 작동
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SiteAddActivity.this);
                            if (success) {
                                dialog = builder.setMessage("추가할 수 있는 사이트입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                plus_site.setEnabled(false);
                                validate = true;
                                btn_site.setText("확인");
                            } else {
                                dialog = builder.setMessage("이미 등록된 사이트입니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SiteValidateRequest SiteValidateRequest = new SiteValidateRequest(plusSite, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SiteAddActivity.this);
                queue.add(SiteValidateRequest);
            }
        });

        //추가 버튼 클릭 시 수행
        Button btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어있는 값을 가져온다,get해온다
                String plusID = plus_id.getText().toString();
                String plusPass = plus_pass.getText().toString();
                String plusPass2 = plus_pass2.getText().toString();
                String plusSite = plus_site.getText().toString();

                if (plusID.equals("") || plusPass.equals("") || plusPass2.equals("") || plusSite.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteAddActivity.this);
                    dialog = builder.setMessage("빈 칸이 존재합니다.") //정상 작동
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                } else if (!plusPass.equals(plusPass2)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteAddActivity.this);
                    dialog = builder.setMessage("비밀번호가 일치하지 않습니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "사이트 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SiteAddActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {//회원등록 실패한 경우 이거 모르겟음.ㅎ
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley 를 이용해서 요청을 함
                SiteAddRequest siteaddRequest = new SiteAddRequest(plusID, plusPass, plusSite, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SiteAddActivity.this);
                queue.add(siteaddRequest);
            }
        });

    }


    public void openActivity2() {
        Intent intent = new Intent(SiteAddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}