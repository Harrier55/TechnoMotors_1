package ru.project.technomotors_1.ui.service;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import ru.project.technomotors_1.CustomAdapter;
import ru.project.technomotors_1.FragmentItemMenuListener;
import ru.project.technomotors_1.R;

public class ServiceFragment extends ListFragment {

    // ВАЖНО - количество картинок должно быть = количеству пунктов меню

    int[]imageService = {
            R.drawable.service_item_1,
            R.drawable.service_item_2,
            R.drawable.service_item_3,
            R.drawable.service_item_4,
            R.drawable.service_item_5};

    private FragmentItemMenuListener fragmentListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] mItem = getResources().getStringArray(R.array.item_menu_service_fragment);

        CustomAdapter customAdapter = new CustomAdapter(getActivity(),mItem,imageService);
        setListAdapter(customAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       if(context instanceof FragmentItemMenuListener){
           fragmentListener = (FragmentItemMenuListener)context;
       } else {
           throw new RuntimeException(context.toString() + "NO IMPLEMENT SERVICE FRAGMENT");
       }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_service, container, false);

        return root;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //Toast.makeText(getActivity(),"Выбрали позицию__" + position,Toast.LENGTH_SHORT).show();
        fragmentListener.onOpenFragment(position + 30);
    }
}