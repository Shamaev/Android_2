package com.geekbrains.weather;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SettingFragment extends Fragment {
    EditText edit_city;
    Button apply_btn;
    Switch switch_info;
    TextView city_name, info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        clickApplyBtn();
    }

    private void initView() {
        edit_city = getActivity().findViewById(R.id.edit_city);
        switch_info = getActivity().findViewById(R.id.switch_info);
        apply_btn = getActivity().findViewById(R.id.setting_Ok);
        city_name = getActivity().findViewById(R.id.city_name);
        info = getActivity().findViewById(R.id.info);
    }

//    private void clickApplyBtn() {
//        apply_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = edit_city.getText().toString();
//                city_name.setText(str);
//                if (switch_info.isChecked()){
//                    info.setText(R.string.cloudy);
//                } else {
//                    info.setText("");
//                }
//            }
//        });
//    }
    private void clickApplyBtn() {
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toActivity(edit_city.getText().toString());
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    public void toActivity(String data) {
//        Activity activity = getActivity();
//        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
//            ((MainActivity) activity).fromFragmentData(data);
//        }
//    }
}
