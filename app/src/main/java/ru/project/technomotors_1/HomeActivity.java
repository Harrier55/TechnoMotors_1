package ru.project.technomotors_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

// здесь я решил не использовать ViewModel но оставил все ее классы
// решил что она здесь не нужна т.к. ориентация только вертикальная


public class HomeActivity extends AppCompatActivity implements FragmentItemMenuListener,SendForm {

    FloatingActionButton fabAction;
    FloatingActionButton fab_tel_1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        initFAB();

        navView.setBackground(null);

       AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_car, R.id.navigation_service)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

       //  NavigationUI надо отключить, если будет включена тема NoActionBar,иначе будет ошибка

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }



    public void initFAB(){
        fabAction = findViewById(R.id.fab_action);
        fab_tel_1 = findViewById(R.id.fab_telefon_1);

        fabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fab_tel_1.getVisibility()== View.INVISIBLE){

                    fab_tel_1.setVisibility(View.VISIBLE);
                    fab_tel_1.setClickable(true);

                }else {
                    fab_tel_1.setVisibility(View.INVISIBLE);
                    fab_tel_1.setClickable(false);
                }
            }
        });


    }

// Интерфейс меню выбора в главных фрагментах
    @Override
    public void onOpenFragment(int value) {
        Toast.makeText(this,"Интерфейс onOpenFragment в Активити получил пункт меню _"
                + value,Toast.LENGTH_SHORT).show();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        switch (value){
            case 30: navController.navigate(R.id.maintenanceFormFragment);
            break;
        }

    }

//  реализация стрелки BackStack
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int itemId =  item.getItemId();
//        Toast.makeText(this,"ItemId" + itemId, Toast.LENGTH_SHORT).show(); // поиск ID элемента ActionBar
        if(itemId == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onBackPressed() {

//       int count = getSupportFragmentManager().getBackStackEntryCount();
       NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
       int current_id_fragment = Objects.requireNonNull(navController.getCurrentDestination()).getId();

//  Реализация желаемой обратной навигации BackStack

       switch (current_id_fragment){
           case R.id.select_contact_client:
           case R.id.select_model_list:
           case R.id.select_year_list:
           case R.id.select_TO_list:
           case R.id.select_data:
           case R.id.select_time:
           case R.id.textView:
               navController.navigate(R.id.maintenanceFormFragment);break;
           case R.id.maintenanceFormFragment:navController.navigate(R.id.navigation_service);
           break;
       }

//        super.onBackPressed();   //  метод специально закомментировал, с ним не всегда корректно работает

    }


    @Override
    public void sendFormTO(FormTO formTO) {

        String url ="https://www.yandex.ru";

        Gson gson = new GsonBuilder().create();
        String jsonFormTO = gson.toJson(formTO);
        SendPost sendPost = new SendPost();
        sendPost.sendForm(url,jsonFormTO);
//        Toast.makeText(this,"Нажата кнопка Send" + jsonFormTO,Toast.LENGTH_SHORT).show();
    }


}