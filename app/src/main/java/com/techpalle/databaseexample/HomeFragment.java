package com.techpalle.databaseexample;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    MyDB db;
    TextView textViewUserName, textViewEmail, textViewPassword;
    Button buttonLogout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MyDB(getActivity());
        db.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        textViewUserName = (TextView) v.findViewById(R.id.user_name);
        textViewEmail = (TextView) v.findViewById(R.id.email);
        textViewPassword = (TextView) v.findViewById(R.id.password);
        buttonLogout = (Button) v.findViewById(R.id.button_logout);
        Bundle b = getArguments();
        String email = b.getString("email");
        Cursor c = db.queryLogin(email);
        if(c != null){
            c.moveToNext();
            textViewUserName.setText("Name: "+c.getString(c.getColumnIndex("name")));
            textViewEmail.setText("Email: "+c.getString(c.getColumnIndex("email")));
            textViewPassword.setText("Password: "+c.getString(c.getColumnIndex("password")));
        }
        c.close();
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.buttonSignIn();
            }
        });
        return v;
    }

}
