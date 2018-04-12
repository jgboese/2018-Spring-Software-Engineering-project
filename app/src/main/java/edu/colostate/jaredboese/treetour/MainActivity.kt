package edu.colostate.jaredboese.treetour

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    internal var mAuthListener: FirebaseAuth.AuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        if (firebaseAuth.currentUser == null) {
            startActivity(Intent(this, GoogleLogin::class.java))
        }
    }
    internal var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onResume() {
        super.onResume()
        mAuth.addAuthStateListener(mAuthListener)
        startActivity(Intent(this,mapActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dataBtn = findViewById<Button>(R.id.DataButton)
        dataBtn.setOnClickListener {
            val intent = Intent(this,ViewData::class.java)
            startActivity(intent)
        }
    }
}