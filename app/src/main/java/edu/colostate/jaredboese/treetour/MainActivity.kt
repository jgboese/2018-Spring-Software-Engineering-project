package edu.colostate.jaredboese.treetour

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var NewBtn = findViewById<Button>(R.id.AButton)

        NewBtn.setOnClickListener {
            val intent = Intent(this,SampleActivity::class.java)
            startActivity(intent)
        }
    }
}

