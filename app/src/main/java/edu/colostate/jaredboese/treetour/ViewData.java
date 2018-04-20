package edu.colostate.jaredboese.treetour;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Jared Boese on 2/19/2018.
 */

public class ViewData extends CoreActivity {
    private ListView mListview;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ArrayList mArray;
    private String mKey;
    private String treename;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            treename = extras.getString("TreeName");
            //The key argument here must match that used in the other activity
        }
            mRef = FirebaseDatabase.getInstance().getReference().child(treename);
            ImageView mImageview = (ImageView) findViewById(R.id.imageView);
            if (treename.equals("CanadaRed")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.canada, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.canada));
                }
            } else if (treename.equals("WeepingMulberry")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.weeping, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.weeping));
                }
            } else if (treename.equals("Hackberry")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.hakberry, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.hakberry));
                }
            } else if (treename.equals("Giant Sequoia")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.giant, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.giant));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ivory, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ivory));
                }
            }


        mListview = (ListView) findViewById(R.id.listview);
        mArray = new ArrayList();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mArray);

        mListview.setAdapter(arrayAdapter);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                // String sdate = dataSnapshot.getValue()
                mArray.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}