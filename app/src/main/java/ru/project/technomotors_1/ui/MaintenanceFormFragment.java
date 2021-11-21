package ru.project.technomotors_1.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.project.technomotors_1.R;
import ru.project.technomotors_1.data.SendForm;


public class MaintenanceFormFragment extends Fragment implements View.OnClickListener {

    private SendForm sendForm; // объект интерфейса

    private static String model;
    private static String year;
    private static String service;
    private static String date;
    private static String time;
    private static String name;
    private static String number_phone;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendForm) {
            sendForm = (SendForm) context;
        } else {
            throw new RuntimeException(context.toString() + "NO IMPLEMENT Interface SendForm");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        if (getArguments() != null) {
//
//            String v_model = getArguments().getString("model");
//            String v_year = getArguments().getString("year");
//            String v_service = getArguments().getString("service");
//            String v_date = getArguments().getString("date");
//            String v_time = getArguments().getString("time");
//            String v_name = getArguments().getString("name");
//            String v_number_phone = getArguments().getString("number_phone");
//
//            if(v_model!= null){model = v_model;}
//            if(v_year != null){year = v_year;}
//            if(v_service != null){service = v_service;}
//            if(v_date != null){date = v_date;}
//            if(v_time != null){time = v_time;}
//            if(v_name != null){name = v_name;}
//            if(v_number_phone != null){number_phone = v_number_phone;}
//        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maintenance_form, container, false);


        // init
        TextView call_model = view.findViewById(R.id.tv_select_model);
        TextView call_year = view.findViewById(R.id.tv_select_year);
        TextView call_TO = view.findViewById(R.id.tv_select_service);
        TextView call_date = view.findViewById(R.id.tv_select_date);
        TextView call_time = view.findViewById(R.id.tv_select_time);
        TextView call_number_phone = view.findViewById(R.id.tv_select_number_phone);
        TextView call_name = view.findViewById(R.id.tv_select_name);
        Button send_form_TO = view.findViewById(R.id.button_send_form_TO);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result != null) {
                    // проверка и установка значений

                    String v_model = result.getString("model");
                    String v_year = result.getString("year");
                    String v_service = result.getString("service");
                    String v_date = result.getString("date");
                    String v_time = result.getString("time");
                    String v_name = result.getString("name");
                    String v_number_phone = result.getString("number_phone");

                    if (v_model != null) {
                        model = v_model;
                        call_model.setText(model);
                    }
                    if (v_year != null) {
                        year = v_year;
                        call_year.setText(year);
                    }
                    if (v_service != null) {
                        service = v_service;
                        call_TO.setText(service);
                    }
                    if (v_date != null) {
                        date = v_date;
                        call_date.setText(date);
                    }
                    if (v_time != null) {
                        time = v_time;
                        call_time.setText(time);
                    }
                    if (v_name != null) {
                        name = v_name;
                        call_name.setText(name);
                    }
                    if (v_number_phone != null) {
                        number_phone = v_number_phone;
                        call_number_phone.setText(number_phone);
                    }


//                    if ((model = result.getString("model")) != null) {
//                        call_model.setText(model);
//                    }
//                    if ((year = result.getString("year")) != null) {
//                        call_year.setText(year);
//                    }
//                    if ((service = result.getString("service")) != null) {
//                        call_TO.setText(service);
//                    }
//                    if ((date = result.getString("date")) != null) {
//                        call_date.setText(date);
//                    }
//                    if ((time = result.getString("time")) != null) {
//                        call_time.setText(time);
//                    }
//                    if ((number_phone = result.getString("number_phone")) != null) {
//                        call_number_phone.setText(number_phone);
//                    }
//                    if ((name = result.getString("name")) != null) {
//                        call_name.setText(name);
//                    }

                }
            }
        });

        // установка значений
        call_model.setText(model);
        call_year.setText(year);
        call_TO.setText(service);
        call_date.setText(date);
        call_time.setText(time);
        call_number_phone.setText(number_phone);
        call_name.setText(name);

        // обработка нажатие на кнопку
        call_model.setOnClickListener(this);
        call_year.setOnClickListener(this);
        call_TO.setOnClickListener(this);
        call_date.setOnClickListener(this);
        call_time.setOnClickListener(this);
        call_number_phone.setOnClickListener(this);
        call_name.setOnClickListener(this);
        send_form_TO.setOnClickListener(this);

        return view;
    }

    public void sendFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("mod")
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_select_model: sendFragment(new SelectModelFragment());
                break;
            case R.id.tv_select_year: sendFragment(new SelectYearFragment());
                break;
            case R.id.tv_select_service: sendFragment(new SelectTOFragment());
                break;
            case R.id.tv_select_date: sendFragment(new SelectDataFragment());
                break;
            case R.id.tv_select_time: sendFragment(new SelectTimeFragment());
                break;
            case R.id.tv_select_number_phone: sendFragment(new SelectContactClientFragment());
                break;
            case R.id.tv_select_name: sendFragment(new SelectContactClientFragment());
                break;
            case R.id.button_send_form_TO: sendFormTO();
                break;

        }
    }

    void sendFormTO() {
//        FormTO formTO = new FormTO();
//        formTO.model = model;
//        formTO.year = year;
//        formTO.service = service;
//        formTO.date = date;
//        formTO.time = time;
//        formTO.name = name;
//        formTO.number_phone = number_phone;
//
//        sendForm.sendFormTO(formTO);

    }


}