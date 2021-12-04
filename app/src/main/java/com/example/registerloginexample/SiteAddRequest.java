package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SiteAddRequest extends StringRequest {
    //서버 URL 설정( PHP 파일 연동)
    final static private String URL = "http://ynns1217.ivyro.net/Siteplus.php";
    private Map<String, String> map;


    public SiteAddRequest(String plusID, String plusPass, String plusSite, String plusUrl, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("plusID", plusID);
        map.put("plusPassword", plusPass);
        map.put("plusSite", plusSite);
        map.put("plusUrl", plusUrl);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}