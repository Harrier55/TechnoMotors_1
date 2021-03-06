package ru.project.technomotors_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SelectDataFragment extends Fragment {

    private static final int PERIOD = 10; // период активных дней для записи

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // эта строка дает текущую дату
        //String currentDate = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,PERIOD); // уведичение текущей даты на требуемый PERIOD

        View root = inflater.inflate(R.layout.fragment_select_data, container, false);

        CalendarView calendarView = root.findViewById(R.id.calendarView);

        long today =  calendarView.getDate(); // получена текущая дата

        //SimpleDateFormat dateFormat = new SimpleDateFormat();
        //String newDate1 = dateFormat.format(calendar.getTime());  //для вывода даты в виде String
        long newDate = calendar.getTimeInMillis();

        // установка дат нужного интервала
        calendarView.setMinDate(today);
        calendarView.setMaxDate(newDate);

        calendarView.setOnDateChangeListener((calendarView1, year, month, day) -> {

            String month1 = new SimpleDateFormat("MMMM", new Locale("ru")).format(calendar.getTime());
            String selectedDate = day +
                    "  " + month1 +
                    "  " + year +
                    " года";
           //Toast.makeText(getActivity(),"Нажата кнопка" + selectedDate , Toast.LENGTH_SHORT).show();
            NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            bundle.putString("date",selectedDate);
            navController.navigate(R.id.maintenanceFormFragment,bundle);
        });

        return root;

    }


}