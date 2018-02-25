package com.example.liqfo.catalogtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.liqfo.catalogtest.Fragments.CheckIn.CheckInFragment;
import com.example.liqfo.catalogtest.Fragments.ObjectList.ObjectsListFragment;

public class MainActivity extends IdleActivity implements MainView, CheckListener {

    private MainPresenter mainPresenter;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        mainPresenter = new MainPresenter(this, getApplicationContext());
        mainPresenter.onCreate();
        mainPresenter.checkStatusCheckIn();
    }

    private void showFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    @Override
    public void showCheckInFragment() {
        showFragment(new CheckInFragment());
    }

    @Override
    public void showObjectsListFragment() {
        showFragment(new ObjectsListFragment());
    }

    @Override
    public void onRegClick(String userName) {
        mainPresenter.setUserName(userName);
    }

    @Override
    public void onLogout() {
        mainPresenter.onLogout();
    }
}
