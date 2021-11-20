package ru.project.technomotors_1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class ContactClientFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_client, container, false);

        EditText enter_name = view.findViewById(R.id.et_enter_name);
        EditText enter_number_phone = view.findViewById(R.id.et_number_phone);
        Button send_contact = view.findViewById(R.id.bt_contact_fragment);

        send_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enter_name.getText().toString();
                String number_phone = enter_number_phone.getText().toString();
//                if(enter_name == null||enter_number_phone == null){
//                Toast.makeText(getActivity(),"введено значение"+ name+number_phone , Toast.LENGTH_SHORT).show();
//                }
 // код для скрытия клавиатуры при переходе в другой фрагмент
                Activity activity = getActivity();
                InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                try {
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e){}
/////////////
                NavController navController = Navigation.findNavController(getActivity(),R.id.fragment_container);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("number_phone",number_phone);
//                navController.navigate(R.id.maintenanceFormFragment,bundle);
            }
        });

        return view;
    }

    
}