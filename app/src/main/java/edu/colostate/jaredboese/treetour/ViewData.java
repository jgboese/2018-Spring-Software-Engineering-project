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
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.canadared, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.canadared));
                }
            }
            else if (treename.equals("WeepingMulberry")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.weepingmullberry, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.weepingmullberry));
                }
            } else if (treename.equals("Hackberry")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.hackberry, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.hackberry));
                }
            } else if (treename.equals("Giant Sequoia")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.giantsequoiah, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.giantsequoiah));
                }
            }
            else if (treename.equals("AmericanElm")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.americanelm, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.americanelm));
                }
            }
            else if (treename.equals("AmericanLinden")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.alinden, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.alinden));
                }
            }
            else if (treename.equals("Apricot")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.apricot, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.apricot));
                }
            }
            else if (treename.equals("Aristocrat Pear")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.aristocratpear, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.aristocratpear));
                }
            }
            else if (treename.equals("AutumnBlaze")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.autumnblaze, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.autumnblaze));
                }

            }
            else if (treename.equals("AutumnPurple")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.autpurpash, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.autpurpash));
                }
            }
            else if (treename.equals("Betchel Crabapple")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.betchelcrab, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.betchelcrab));
                }
            }
            else if (treename.equals("Bur Oak")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.buroak, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.buroak));
                }
            }
            else if (treename.equals("CrimsonCloud")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ccloud, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ccloud));
                }
            }
            else if (treename.equals("CrimsonKing")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.crimsonking, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.crimsonking));
                }
            }
            else if (treename.equals("Bluespruce")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.cobluespruce, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.cobluespruce));
                }
            }
            else if (treename.equals("Dawn Redwood")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.dawnredwood, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.dawnredwood));
                }
            }
            else if (treename.equals("EnglishOak")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.englishoak, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.englishoak));
                }
            }
            else if (treename.equals("Flamingo Boxelder")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.flamingo, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.flamingo));
                }
            }
            else if (treename.equals("Ginkgo")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ginkgo, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ginkgo));
                }
            }
            else if (treename.equals("GreenAsh")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.greenash, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.greenash));
                }
            }
            else if (treename.equals("Dawn Redwood")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.dawnredwood, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.dawnredwood));
                }
            }
            else if (treename.equals("KentuckyCoffee")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.kycoffeetree, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.kycoffeetree));
                }
            }
            else if (treename.equals("LittleleafLinden")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.littleleaflinden, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.littleleaflinden));
                }
            }
            else if (treename.equals("LondonPlane")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.londonplaneleaf, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.londonplaneleaf));
                }
            }
            else if (treename.equals("MountainAsh")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.mountainash, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.mountainash));
                }
            }
            else if (treename.equals("Mulberry")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.mulberry, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.mulberry));
                }
            }
            else if (treename.equals("NorwayMaple")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.norwaymaple, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.norwaymaple));
                }
            }
            else if (treename.equals("OrnaPear")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ornamentalpear, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ornamentalpear));
                }
            }
            else if (treename.equals("PinyonPine")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.pinyonpin, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.pinyonpin));
                }
            }
            else if (treename.equals("PonderosaPine")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ponderosapine, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.ponderosapine));
                }
            }
            else if (treename.equals("RedspirePear")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.redspirepear, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.redspirepear));
                }
            }
            else if (treename.equals("ScotchPine")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.scotchpine, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.scotchpine));
                }
            }
            else if (treename.equals("Sensation")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.sensationboxelder, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.sensationboxelder));
                }
            }
            else if (treename.equals("SiberianElm")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.siberianelm, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.siberianelm));
                }
            }
            else if (treename.equals("SpringSnow")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.springsnow, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.springsnow));
                }
            }
            else if (treename.equals("SugarMaple")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.sugarmaple, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.sugarmaple));
                }
            }
            else if (treename.equals("Swamp WhiteOak")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.swampwhiteoak, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.swampwhiteoak));
                }
            }
            else if (treename.equals("Thornless Cockspur")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.cockspur, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.cockspur));
                }
            }
            else if (treename.equals("TurkishFilbert")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.turkish, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.turkish));
                }
            }
            else if (treename.equals("WesternCatalpa")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.wcatalpa, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.wcatalpa));
                }
            }
            else if (treename.equals("Tuliptree")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.tulippop, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.tulippop));
                }
            }
            else if (treename.equals("WinterKing")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.winterking, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.winterking));
                }
            }
            else if (treename.equals("Yellowhorn")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.yellowhorn, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.yellowhorn));
                }
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.stocktree, getApplicationContext().getTheme()));
                } else {
                    mImageview.setImageDrawable(getResources().getDrawable(R.drawable.stocktree));
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