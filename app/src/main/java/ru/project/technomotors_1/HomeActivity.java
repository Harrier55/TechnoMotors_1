package ru.project.technomotors_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;
import java.util.logging.LogManager;

import ru.project.technomotors_1.data.FormTO;
import ru.project.technomotors_1.ui.car.CarFragment;
import ru.project.technomotors_1.ui.home.HomeFragment;
import ru.project.technomotors_1.ui.service.ServiceFragment;

// здесь я решил не использовать ViewModel но оставил все ее классы
// решил что она здесь не нужна т.к. ориентация только вертикальная


public class HomeActivity extends AppCompatActivity implements FragmentItemMenuListener, SendForm {
    
    public static final String TAG = "@@@";

    private FloatingActionButton fabAction;
    private FloatingActionButton fab_tel_1;
    public BottomNavigationView bottomNavigationView;

    public HomeFragment homeFragment = new HomeFragment();
    public CarFragment carFragment = new CarFragment();
    public ServiceFragment serviceFragment = new ServiceFragment();

    public  FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();
        launchFragmentFromBottomNavigation(homeFragment,R.string.title_home);
        initFAB();
        initBottomNavigation();

    }




    private void initBottomNavigation() {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_car:
                        launchFragmentFromBottomNavigation(carFragment, R.string.title_car);
                        break;
                    case R.id.navigation_service:
                        launchFragmentFromBottomNavigation(serviceFragment,R.string.title_service);
                        break;
                    default: launchFragmentFromBottomNavigation(homeFragment,R.string.title_home);
                        break;
                }
                return false;
            }
        });

    }


    public void launchFragmentFromBottomNavigation(Fragment fragment, int title) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        setTitle(title);
    }

    public void initFAB() {
        fabAction = findViewById(R.id.fab_action);
        fab_tel_1 = findViewById(R.id.fab_telefon_1);

        fabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fab_tel_1.getVisibility() == View.INVISIBLE) {

                    fab_tel_1.setVisibility(View.VISIBLE);
                    fab_tel_1.setClickable(true);

                } else {
                    fab_tel_1.setVisibility(View.INVISIBLE);
                    fab_tel_1.setClickable(false);
                }
            }
        });


    }

    // Интерфейс меню выбора в главных фрагментах
    @Override
    public void onOpenFragment(int value) {
        Toast.makeText(this, "Интерфейс onOpenFragment в Активити получил пункт меню _"
                + value, Toast.LENGTH_SHORT).show();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);

        switch (value) {
            case 30:
//                navController.navigate(R.id.maintenanceFormFragment);
                break;
        }

    }

    //  реализация стрелки BackStack
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
//        Toast.makeText(this,"ItemId" + itemId, Toast.LENGTH_SHORT).show(); // поиск ID элемента ActionBar
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onBackPressed() {

//       int count = getSupportFragmentManager().getBackStackEntryCount();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        int current_id_fragment = Objects.requireNonNull(navController.getCurrentDestination()).getId();

//  Реализация желаемой обратной навигации BackStack

        switch (current_id_fragment) {
//            case R.id.select_contact_client:
//            case R.id.select_model_list:
//            case R.id.select_year_list:
//            case R.id.select_TO_list:
//            case R.id.select_data:
//            case R.id.select_time:
//            case R.id.textView:
//                navController.navigate(R.id.maintenanceFormFragment);
//                break;
//            case R.id.maintenanceFormFragment:
//                navController.navigate(R.id.navigation_service);
//                break;
        }

//        super.onBackPressed();   //  метод специально закомментировал, с ним не всегда корректно работает

    }


    @Override
    public void sendFormTO(FormTO formTO) {

        String url = "https://www.yandex.ru";

        Gson gson = new GsonBuilder().create();
        String jsonFormTO = gson.toJson(formTO);
        SendPost sendPost = new SendPost();
        sendPost.sendForm(url, jsonFormTO);
//        Toast.makeText(this,"Нажата кнопка Send" + jsonFormTO,Toast.LENGTH_SHORT).show();
    }


}