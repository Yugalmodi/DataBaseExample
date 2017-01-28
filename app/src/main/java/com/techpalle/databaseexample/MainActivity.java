package com.techpalle.databaseexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SignupFragment signupFragment  = new SignupFragment();
    SignInFragment signInFragment = new SignInFragment();
    ViewDatabaeFragment databaeFragment = new ViewDatabaeFragment();
    HomeFragment homeFragment = new HomeFragment();
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, signupFragment);
        transaction.commit();
    }

    public void createAnAccount(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, signupFragment);
        transaction.commit();
    }

    public void buttonDatabase(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, databaeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void buttonSignIn(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, signInFragment);
        transaction.commit();
    }

    public void buttonLogin(String email){
        Bundle b = new Bundle();
        b.putString("email", email);
        homeFragment.setArguments(b);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.commit();

    }
}
