package edu.colostate.jaredboese.treetour;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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