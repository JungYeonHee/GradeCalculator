package com.example.gradecalculator.management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gradecalculator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagementFragment extends Fragment {

    public ManagementFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ManagementFragment newInstance(String param1, String param2) {
        ManagementFragment fragment = new ManagementFragment();
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
        //View view = inflater.inflate(R.layout.fragment_management, container, false);
        return inflater.inflate(R.layout.fragment_management, container, false);
    }
}