package ru.project.technomotors_1.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import ru.project.technomotors_1.R;


public class SelectTimeFragment extends Fragment {

    private GridView gvSelectTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_time, container, false);
        gvSelectTime = view.findViewById(R.id.gvSelectTime);
        String[] mItem = getResources().getStringArray(R.array.item_time);
        gvSelectTime.setAdapter( new ArrayAdapter<>(getActivity(),R.layout.item_time_fragment,R.id.tvItemTime,mItem));

        gvSelectTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String time = adapterView.getItemAtPosition(position).toString();

                Bundle bundle = new Bundle();
                bundle.putString("time",time);
                getParentFragmentManager().setFragmentResult("requestKey",bundle);
                getParentFragmentManager().popBackStack();



                //Toast.makeText(getActivity(),"выбрано время"+ time ,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}