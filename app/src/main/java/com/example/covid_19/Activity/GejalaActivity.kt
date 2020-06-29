package com.example.covid_19.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.example.covid_19.model.GejalaModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_gejala.*

class GejalaActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gejala)

        fab.setOnClickListener {
            startActivity(Intent(this, AddSymptomsActivity::class.java))
            finish()
        }

        val databaseRef = FirebaseDatabase.getInstance().getReference("Gejala")
        val gejala: ArrayList<Gejala> = ArrayList()

        recyclerView.layoutManager = LinearLayoutManager(this)

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                println("Info : ${error.message}")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                gejala.clear()
                for (datasnapshot in snapshot.children){
                    val getValue = datasnapshot.getValue(Gejala::class.java)
                    getValue?.let {gejala.add(it)}
                }
            }

        })

        recyclerView.adapter = GejalaAdapter(this, gejala)
        }
    }

