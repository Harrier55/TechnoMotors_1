package ru.project.technomotors_1;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.project.technomotors_1.data.FormTO;
import ru.project.technomotors_1.data.SendForm;
import ru.project.technomotors_1.data.SendPost;
import ru.project.technomotors_1.ui.FragmentItemMenuListener;
import ru.project.technomotors_1.ui.MaintenanceFormFragment;
import ru.project.technomotors_1.ui.car.CarFragment;
import ru.project.technomotors_1.ui.home.HomeFragment;
import ru.project.technomotors_1.ui.service.ServiceFragment;

// здесь я решил не использовать ViewModel но оставил все ее классы
// решил что она здесь не нужна т.к. ориентация только вертикальная


public class HomeActivity extends AppCompatActivity implements FragmentItemMenuListener, SendForm {

    private static final int SERVICE_ITEM_1 = 30;
    
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

    void launcherFragmentWithAddToBackStack(Fragment fragment,int title){
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
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
//        NavController navController = Navigation.findNavController(this, R.id.fragment_container);

        switch (value) {
            case 30:
                launcherFragmentWithAddToBackStack(new MaintenanceFormFragment(),R.string.title_maintenanceFormFragment);
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


    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            showClosingDialog();
//            super.onBackPressed();
        }
    }

    public void showClosingDialog(){
        new AlertDialog.Builder(this)

                .setPositiveButton("Ухожу", (dialogInterface, i) -> {
                    Toast.makeText(this, "Пока...пока", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Остаюсь",null)
                .show();
    }


    @Override
    public void sendFormTO(FormTO formTO) {

        String url = "https://www.yandex.ru";

        Gson gson = new GsonBuilder().create();
        String jsonFormTO = gson.toJson(formTO);
        SendPost sendPost = new SendPost();
//        sendPost.sendForm(url, jsonFormTO);
//        Toast.makeText(this,"Нажата кнопка Send" + jsonFormTO,Toast.LENGTH_SHORT).show();
    }


}