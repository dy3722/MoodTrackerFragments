package com.example.moodtrackerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NeutralFragment extends Fragment {
    private TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neutral, container, false);
        tv = view.findViewById(R.id.textViewN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            EditText et = getActivity().findViewById(R.id.et);
            if (et != null && tv != null) {
                tv.setText("היי " + et.getText().toString() + "!");
            }
        }
    }

    public void updateName(String name) {
        if (tv != null) tv.setText("היי " + name + "!");
    }
}