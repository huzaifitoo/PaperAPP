package com.example.paperapp.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paperapp.Adapters.ClassAdapter
import com.example.paperapp.Model.ClassModel
import com.example.paperapp.R
import com.example.paperapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var list: ArrayList<ClassModel>

    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        firebaseDatabase = FirebaseDatabase.getInstance()

        list = ArrayList()

        val adapter = ClassAdapter(list, this)

        val layoutManager = GridLayoutManager(this, 2)

        binding.classRv.layoutManager = layoutManager

        firebaseDatabase.reference.child("classes")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    list.clear()

                    if (snapshot.exists()) {

                        for (dataSnapshot in snapshot.children) {

                            val data = dataSnapshot.getValue(ClassModel::class.java)

                            data?.classId = dataSnapshot.key?.toInt()!!

                            list.add(data!!)

                            Log.d("DATA", data.className)

                        }

                        adapter.notifyDataSetChanged()

                        binding.classRv.adapter = adapter


                    }

                }

                override fun onCancelled(error: DatabaseError) {

                    Log.d("DATA", error.message)

                }

            })


    }
}