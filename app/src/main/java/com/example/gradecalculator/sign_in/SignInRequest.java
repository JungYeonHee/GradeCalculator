package com.example.gradecalculator.sign_in;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignInRequest extends StringRequest {
    //서버 URL 설정(PHP 파일 연동)
    final static private String URL = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/SignIn.php";
    private Map<String, String> map;

    public SignInRequest(String userID, String password, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
