package com.example.gradecalculator.sign_up;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
        //서버 URL 설정(PHP 파일 연동)
        final static private String URL = "http://ec2-52-79-221-73.ap-northeast-2.compute.amazonaws.com/SignIn.php";
        private Map<String, String> map;

    public SignUpRequest(int userID, String userName, String password, int toeic, String schoolYear, String semester, String mainMajor, String _2ndMajorClass, String _2ndMajor, String thesis, Response.Listener<String> listener){
            super(Request.Method.POST, URL, listener, null);

            map = new HashMap<>();
            map.put("userID", userID + ""); //string 이라고 눈속임
            map.put("userName", userName);
            map.put("password", password);
            map.put("toeic", toeic + "");
            map.put("schoolYear", schoolYear);
            map.put("semester", semester);
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
