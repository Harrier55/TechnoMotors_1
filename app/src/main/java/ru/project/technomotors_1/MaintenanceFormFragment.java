package ru.project.technomotors_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.project.technomotors_1.data.FormTO;


public class MaintenanceFormFragment extends Fragment implements View.OnClickListener{

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
        if(context instanceof SendForm){
            sendForm = (SendForm)context;
        }else {
            throw new RuntimeException(context.toString() + "NO IMPLEMENT Interface SendForm");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {

            String v_model = getArguments().getString("model");
            String v_year = getArguments().getString("year");
            String v_service = getArguments().getString("service");
            String v_date = getArguments().getString("date");
            String v_time = getArguments().getString("time");
            String v_name = getArguments().getString("name");
            String v_number_phone = getArguments().getString("number_phone");

            if(v_model!= null){model = v_model;}
            if(v_year != null){year = v_year;}
            if(v_service != null){service = v_service;}
            if(v_date != null){date = v_date;}
            if(v_time != null){time = v_time;}
            if(v_name != null){name = v_name;}
            if(v_number_phone != null){number_phone = v_number_phone;}
        }
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
        TextView call_name= view.findViewById(R.id.tv_select_name);
        Button send_form_TO = view.findViewById(R.id.button_send_form_TO);

        // обработка нажатие на кнопку
        call_model.setOnClickListener(this);
        call_year.setOnClickListener(this);
        call_TO.setOnClickListener(this);
        call_date.setOnClickListener(this);
        call_time.setOnClickListener(this);
        call_number_phone.setOnClickListener(this);
        call_name.setOnClickListener(this);
        send_form_TO.setOnClickListener(this);

        // установка значений
        call_model.setText(model);
        call_year.setText(year);
        call_TO.setText(service);
        call_date.setText(date);
        call_time.setText(time);
        //call_number_phone.setText(phone_number); код был для Alert Dialog
        call_number_phone.setText(number_phone);
        call_name.setText(name);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        NavController navController = Navigation.findNavController(getActivity(),R.id.fragment_container);
        switch (view.getId()){
//            case R.id.tv_select_model: navController.navigate(R.id.select_model_list);break;
//            case R.id.tv_select_year: navController.navigate(R.id.select_year_list);break;
//            case R.id.tv_select_service: navController.navigate(R.id.select_TO_list);break;
//            case R.id.tv_select_date: navController.navigate(R.id.select_data);break;
//            case R.id.tv_select_time: navController.navigate(R.id.select_time);break;
//            case R.id.tv_select_number_phone: navController.navigate(R.id.select_contact_client);break;
//            case R.id.button_send_form_TO:sendFormTO();break;
            //case R.id.tv_select_name: navController.navigate(R.id.contactClientFragment);break;

        }
    }

    void sendFormTO(){
        FormTO formTO = new FormTO();
//        formTO.model = model;
//        formTO.year = year;
//        formTO.service = service;
//        formTO.date = date;
//        formTO.time = time;
//        formTO.name = name;
//        formTO.number_phone = number_phone;

        sendForm.sendFormTO(formTO);

    }





}