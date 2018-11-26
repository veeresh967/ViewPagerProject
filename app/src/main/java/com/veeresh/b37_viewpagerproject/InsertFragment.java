package com.veeresh.b37_viewpagerproject;


import android.os.Bundle;
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
public class InsertFragment extends Fragment {
    //declare all variables
    EditText et1, et2, et3;
    Button b1;
    public InsertFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initialize all variables
        View v = inflater.inflate(R.layout.fragment_insert, container, false);
        et1 = v.findViewById(R.id.editText1);
        et2 = v.findViewById(R.id.editText2);
        et3 = v.findViewById(R.id.editText3);
        b1 = v.findViewById(R.id.button1);
        //done button click - for inserting movie into table
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String actor = et1.getText().toString();
                String moviename = et2.getText().toString();
                String actress = et3.getText().toString();
                //now insert into database
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.myDatabase.insertMovies(actor, moviename, actress);
                Toast.makeText(getActivity(), "INSERTED..", Toast.LENGTH_SHORT).show();
                et1.setText("");et2.setText("");et3.setText("");et1.requestFocus();
            }
        });
        return v;
    }

}
