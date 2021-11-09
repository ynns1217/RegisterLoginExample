package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SiteValidateRequest extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static private String URL = "http://ynns1217.ivyro.net/SiteValidate.php";
    private Map<String,String> map;

    public SiteValidateRequest(String plusSite, Response.Listener<String>listener){
        super(Request.Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("plusSite",plusSite);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}