package com.example.gradecalculator.sign_up;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
        //서버 URL 설정(PHP 파일 연동)
        final static private String URL = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/SignUp.php";
        private Map<String, String> map;
        private Map<String, Integer> map2;


    public SignUpRequest(int userID, String userName, String password, int entYear, int toeic, int schoolYear, int semester, String mainMajor, String _2ndMajorClass, String _2ndMajor, String thesis, Response.Listener<String> listener){
            super(Request.Method.POST, URL, listener, null);

            map = new HashMap<>();
            map2 = new HashMap<>();

            map2.put("userID", userID);
            map.put("userName", userName);
            map.put("password", password);
            map2.put("entYear", entYear);
            map2.put("toeic", toeic);
            map2.put("schoolYear", schoolYear);
            map2.put("semester", semester);
            map.put("mainMajor", mainMajor);
            map.put("_2ndMajorClass", _2ndMajorClass);
            map.put("_2ndMajor", _2ndMajor);
            map.put("thesis", thesis);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return map;
        }

}
