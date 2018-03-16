package com.example.diez.ojs;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


//implements Asynchtask-ya no es aqui - si no en fragment
public class MainActivity extends AppCompatActivity  {

    globales global;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        global =(globales) getApplicationContext();

        //comineza nav
        toolbar = (Toolbar) findViewById(R.id.appbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//no se muesta el menu
        getSupportActionBar().setTitle(R.string.app_name);

        navView = (NavigationView)findViewById(R.id.navview);
        //aqui termina nav

        final Fragment_inicio fragmentInicio = new Fragment_inicio();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragmentInicio )
                .commit();

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new Fragment_inicio();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new Fragment_articulos();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new Fragment_todos_los_numeros();
                                fragmentTransaction = true;
                                break;
                        }
                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment).addToBackStack(null)
                                    .commit();
                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
