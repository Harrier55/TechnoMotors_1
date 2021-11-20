package ru.project.technomotors_1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.project.technomotors_1.R;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView text_home = root.findViewById(R.id.text_home);
        TextView textView2 = root.findViewById(R.id.textView2);

        // пробую передать в MaintenanceFormFragment данные через Bundle

        text_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"text_home",Toast.LENGTH_SHORT).show();
                Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.maintenanceFormFragment,result);
                //getParentFragmentManager().setFragmentResult("requestKey", result);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"text_home55",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}