package com.techpalle.databaseexample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    Button buttonSignIn, buttonSignUp, buttondb;
    EditText editTextName, editTextPwd, editTextCnfmPwd, editTextEmail;
    MyDB db;
    public SignupFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_signup, container, false);
        editTextName = (EditText) v.findViewById(R.id.edit_text_name);
        editTextEmail = (EditText) v.findViewById(R.id.edit_text_email);
        editTextPwd = (EditText) v.findViewById(R.id.edit_text_pwd);
        editTextCnfmPwd = (EditText) v.findViewById(R.id.edit_text_cnfm_pwd);
        buttonSignUp = (Button) v.findViewById(R.id.button_sign_up);
        buttonSignIn = (Button) v.findViewById(R.id.button_sign_in);
        buttondb = (Button) v.findViewById(R.id.button_db);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String pwd = editTextPwd.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String cnfmPwd = editTextCnfmPwd.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty() || pwd.isEmpty() || cnfmPwd.isEmpty()){
                    Toast.makeText(getActivity(), "Please Fill All Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(email.matches(emailPattern)) {
                        if (cnfmPwd.equals(pwd)) {
                            db.insertValues(editTextName.getText().toString().trim(), editTextEmail.getText().toString().trim(),
                                    editTextCnfmPwd.getText().toString().trim());
                            db.close();
                            editTextName.setText("");
                            editTextEmail.setText("");
                            editTextPwd.setText("");
                            editTextCnfmPwd.setText("");
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.buttonSignIn();
                        } else {
                            Toast.makeText(getActivity(), "Password do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "Email format is wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.buttonSignIn();
            }
        });

        buttondb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.buttonDatabase();
            }
        });
        return v;
    }

}
