package edu.colostate.jaredboese.treetour;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jared Boese on 2/19/2018.
 */
public class CoreActivity extends AppCompatActivity implements AutoCloseable {
    public static final String TAG = "Trees";
    Model.Lazy mModel = new Model.Lazy(this);
    @Override public void close() { mModel.close(); }
    @Override public void onDestroy() {
        close();
        super.onDestroy();
    }



    Prefs prefs() { return mModel.self().prefs(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();

        // mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Message is signed in
                    onSignIn();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    onSignOut();
                    // Message is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    void onSignIn() {

    }

    void onSignOut() {

    }
    public FirebaseAuth mAuth;
    public DatabaseReference mRef;

    FirebaseAuth.AuthStateListener mAuthListener;


}
