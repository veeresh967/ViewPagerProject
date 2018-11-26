package com.veeresh.b37_viewpagerproject;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {
    //declare all required variables
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyAdapter myAdapter;
    Cursor cursor;

    //write recyclerview adapter
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        //fill adapter code
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //load row xml
            View v = getActivity().getLayoutInflater().inflate(R.layout.row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //read data from cursor based on the position
            cursor.moveToPosition(position);
            int sno = cursor.getInt(cursor.getColumnIndex("_id"));
            String actor = cursor.getString(cursor.getColumnIndex("actor"));
            String actress = cursor.getString(cursor.getColumnIndex("actress"));
            String moviename = cursor.getString(cursor.getColumnIndex("moviename"));
            //apply data ont the row xml [view holder]
            holder.tv1.setText(""+sno);
            holder.tv2.setText(moviename);
            holder.tv3.setText(actor);
            holder.tv4.setText(actress);
        }
        @Override
        public int getItemCount() {
            if(cursor != null){
                return cursor.getCount();
            }
            return 0;
        }
        public class ViewHolder extends RecyclerView.ViewHolder{
            //declare row xml elements
            public TextView tv1, tv2, tv3, tv4;
            public ViewHolder(View itemView) {
                super(itemView);
                //initialize all views here
                tv1 = itemView.findViewById(R.id.textView1); // FOR DISPLAYING SNO
                tv2 = itemView.findViewById(R.id.textView2); //FOR DISPLAYING MOVIE NAME
                tv3 = itemView.findViewById(R.id.textView3); //FOR DISPLAYING ACTOR NAME
                tv4 = itemView.findViewById(R.id.textView4); //FOR DISPLAYING ACTRESS NAME
            }
        }
    }

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // initialize all variables
        View v = inflater.inflate(R.layout.fragment_show, container, false);
        recyclerView = v.findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        myAdapter = new MyAdapter();
        MainActivity mainActivity = (MainActivity) getActivity();
        cursor = mainActivity.myDatabase.queryMovies();
        //establish links
        recyclerView.setAdapter(myAdapter); //adapter --to--> recyclerview
        recyclerView.setLayoutManager(linearLayoutManager); //recyclerview--to--> layoutmanager
        return v;
    }

}
