package com.example.gradecalculator.graduation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gradecalculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraduationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraduationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    public GraduationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GraduationFragment newInstance(String param1, String param2) {
        GraduationFragment fragment = new GraduationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graduation, container, false);
    }
}