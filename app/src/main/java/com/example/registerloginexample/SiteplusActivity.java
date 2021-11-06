
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

public class  SiteplusActivity extends AppCompatActivity {

    private EditText plus_site, plus_id, plus_pass, plus_pass2;
    private Button btn_site;
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


        //아이디 중복확인 버튼 클릭 시 수행
        btn_site = findViewById(R.id.btn_site);
        btn_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = plus_site.getText().toString();
                if (validate) {
                    return;
                }
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteplusActivity.this);
                    dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다") //정상 작동
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            AlertDialog.Builder builder=new AlertDialog.Builder( SiteplusActivity.this );
                            if(success){
                                dialog=builder.setMessage("추가할 수 있는 사이트입니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                plus_site.setEnabled(false);
                                validate=true;
                                btn_site.setText("확인");
                            }
                            else{
                                dialog=builder.setMessage("추가할 수 없는 사이트입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest ValidateRequest = new ValidateRequest(userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SiteplusActivity.this);
                queue.add(ValidateRequest);
            }
        });


        //추가 버튼 클릭 시 수행
        Button btn_register = findViewById(R.id.btn_plus);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어있는 값을 가져온다,get해온다
                String plusID = plus_id.getText().toString();
                String plusPass = plus_pass.getText().toString();
                String plusPass2 = plus_pass2.getText().toString();
                String plusSite= plus_site.getText().toString();


                if (plusID.equals("")||plusPass.equals("")||plusPass2.equals("")||plusSite.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteplusActivity.this);
                    dialog = builder.setMessage("빈 칸이 존재합니다.") //정상 작동
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                else if (!plusPass.equals(plusPass2)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SiteplusActivity.this);
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
                            if (success) { //회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SiteplusActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{//회원등록 실패한 경우 이거 모르겟음.ㅎ
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //서버로 Volley 를 이용해서 요청을 함
                SiteplusRequest SiteplusRequest = new SiteplusRequest(plusID, plusPass,plusSite,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SiteplusActivity.this);
                queue.add(SiteplusRequest);
            }
        });
    }
}