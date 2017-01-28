package com.techpalle.databaseexample;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDatabaeFragment extends Fragment {
    MyDB db;
    TextView tv;

    public ViewDatabaeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_view_databae, container, false);
        tv = (TextView) v.findViewById(R.id.tv1);
        StringBuilder builder = new StringBuilder();
        Cursor c = db.query();
        if(c != null){
            while(c.moveToNext()){
                int id = c.getInt(0);
                String name = c.getString(1);
                String email = c.getString(2);
                String pwd = c.getString(3);
                builder.append(id+" "+name+" "+email+" "+pwd+"\n");
            }
        }
        tv.setText(builder);
        c.close();
        return v;
    }

}
