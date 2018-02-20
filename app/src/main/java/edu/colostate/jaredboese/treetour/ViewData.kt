package edu.colostate.jaredboese.treetour

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*
import java.util.ArrayList



//
//class ViewData : AppCompatActivity() {
//    private lateinit var mRef: DatabaseReference
//    private val mArraylist = arrayListOf<String>()
//    private val mListview = findViewById<ListView>(R.id.listview)
//    private val TreeList: MutableList<FBDatabase> = mutableListOf()
//    val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArraylist)
//
//    private fun initData() {
//        val treeListener = object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val value = dataSnapshot.getValue(String::class.java)
//                if (value != null) {
//                    mArraylist.add(value)
//                }
//                arrayAdapter.notifyDataSetChanged()
//
//            }
//
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_data);
//        mRef = FirebaseDatabase.getInstance().getReference()
//        mListview.setAdapter(arrayAdapter)
//
//        mRef.child("TreeDat").addListenerForSingleValueEvent(treeListener)
//        }
//    }


