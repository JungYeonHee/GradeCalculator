package com.example.gradecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gradecalculator.graduation.GraduationFragment;
import com.example.gradecalculator.home.HomeFragment;
import com.example.gradecalculator.management.ManagementFragment;
import com.example.gradecalculator.mypage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {
    private TextView tv;

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment frag1;
    private ManagementFragment frag2;
    private GraduationFragment frag3;
    private MyPageFragment frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* tv = findViewById(R.id.tvMain);

        String url = "http://www.google.com";

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    tv.setText("Response is: "+ response.substring(0,500));
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("That didn't work!");
            }
        });

        // Get a RequestQueue && Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
*/
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        setFrag(0);
                        break;
                    case R.id.bottom_management:
                        setFrag(1);
                        break;
                    case R.id.bottom_graduation:
                        setFrag(2);
                        break;
                    case R.id.bottom_mypage:
                        setFrag(3);
                        break;
                }
                return false;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavi);
        frag1 = new HomeFragment();
        frag2 = new ManagementFragment();
        frag3 = new GraduationFragment();
        frag4 = new MyPageFragment();

        setFrag(0); //첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 결정
    }

    //프래그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
        }
    }

}