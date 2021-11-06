package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SiteplusRequest extends StringRequest {
    //서버 URL 설정( PHP 파일 연동)
    final static private String URL = "http://ynns1217.ivyro.net/Siteplus.php";
    private Map<String, String> map;


    public SiteplusRequest(String plusID, String plusPassword, String plusSite, Response.Listener<String>listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("plusID", plusID);
        map.put("plusPassword", plusPassword);
        map.put("plusSite", plusSite);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}