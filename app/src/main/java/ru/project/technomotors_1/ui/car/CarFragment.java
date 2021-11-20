package ru.project.technomotors_1.ui.car;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import ru.project.technomotors_1.CustomAdapter;
import ru.project.technomotors_1.FragmentItemMenuListener;
import ru.project.technomotors_1.R;

public class CarFragment extends ListFragment {

    private FragmentItemMenuListener carFragmentListener;

    int[]imageService = {
            R.drawable.car_item_1,
            R.drawable.car_item_2,
            R.drawable.car_item_3,
            R.drawable.car_item_4,
            R.drawable.car_item_5};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentItemMenuListener){
            carFragmentListener = (FragmentItemMenuListener)context;
        } else {
            throw new RuntimeException(context.toString() + "NO IMPLEMENT CAR FRAGMENT");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] mItem = getResources().getStringArray(R.array.item_menu_car_fragment);

        CustomAdapter customAdapter = new CustomAdapter(getActivity(),mItem,imageService);
        setListAdapter(customAdapter);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

               View root = inflater.inflate(R.layout.fragment_car, container, false);

        return root;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(),"Выбрали позицию__" + (position + 20),Toast.LENGTH_SHORT).show();
        carFragmentListener.onOpenFragment(position + 20);
    }
}