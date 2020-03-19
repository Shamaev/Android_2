package Dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.weather.CityAdapter;
import com.geekbrains.weather.MainActivity;
import com.geekbrains.weather.OnCityItemClickListener;
import com.geekbrains.weather.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private OnDialogListener dialogListener;
    EditText editText;
    Button button;
    String strEdit;
    Switch switch_info;
    private RecyclerView recyclerView;

    public static MyBottomSheetDialogFragment newInstance() {
        return new MyBottomSheetDialogFragment();
    }

    public void setOnDialogListener(OnDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container,
                false);

        setCancelable(false);

        initView(view);
        onClick();
        loadModel();
        return view;
    }



    private void initView(View view) {
        editText = view.findViewById(R.id.edit_city);
        button = view.findViewById(R.id.setting_Ok);
        strEdit = "";
        switch_info = view.findViewById(R.id.switch_info);
        recyclerView = view.findViewById(R.id.list_city);
    }

    public String getEditText() {
        return editText.getText().toString();
    }

    private void onClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                strEdit = editText.getText().toString();
                String check;
                if (switch_info.isChecked()){
                    check = getString(R.string.cloudy);
                } else {
                    check = "";
                }
                toActivity(strEdit, check);
//                if (dialogListener != null) dialogListener.onDialogApply();
            }
        });
    }

    public void toActivity(String data, String checkSwitch) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).fromFragmentData(data, checkSwitch);
        }
    }

    private void loadModel() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        final String[] cities =
                {
                        getResources().getString(R.string.moscow),
                        getResources().getString(R.string.ivanovo)
                };
        recyclerView.setAdapter(new CityAdapter(cities, new OnCityItemClickListener() {
            @Override
            public void OnClick(String data) {
                editText.setText(data);
            }
        }));
    }
}
