package com.techpalle.databaseexample;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    EditText editTextEmail, editTextPwd;
    Button buttonSignIn;
    TextView textView;
    MyDB db;

    public SignInFragment() {
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
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        editTextEmail = (EditText) v.findViewById(R.id.edit_text_sign_in_email);
        editTextPwd = (EditText) v.findViewById(R.id.edit_text_sign_in_pwd);
        buttonSignIn = (Button) v.findViewById(R.id.button_sign_in_signin);
        textView = (TextView) v.findViewById(R.id.text_view_create_an_account);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.createAnAccount();
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPwd.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor c = db.queryLogin(email);
                    if(c!=null){
                        c.moveToNext();
                        int l = c.getColumnIndex("password");
                        if(c.getString(3).equals(password)){
                            editTextEmail.setText("");
                            editTextPwd.setText("");
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.buttonLogin(email);
                        }
                    else{
                        Toast.makeText(getActivity(), "Please check the password", Toast.LENGTH_SHORT).show();
                        editTextPwd.setText("");
                        editTextPwd.requestFocus();
                    }
                    }
                c.close();
                }
            }
        });
        return v;
    }

}
