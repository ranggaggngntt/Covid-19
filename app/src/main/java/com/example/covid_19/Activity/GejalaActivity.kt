package com.example.covid_19.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.example.covid_19.model.GejalaModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_gejala.*

class GejalaActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var gejala: MutableList<Gejala>
    lateinit var gejalaAdapter: GejalaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gejala)

        fab.setOnClickListener {
            startActivity(Intent(this, AddSymptomsActivity::class.java))
            finish()
        }

        ref = FirebaseDatabase.getInstance().getReference("Gejala")
        gejala = mutableListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("Info : ${error.message}")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                gejala.clear()
                for (datasnapshot in snapshot.children) {
                    val getValue = datasnapshot.getValue(Gejala::class.java)
                    gejala.add(getValue!!)
                }

                gejalaAdapter = GejalaAdapter(this@GejalaActivity, gejala as ArrayList<Gejala>)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@GejalaActivity)
                    adapter = gejalaAdapter
                }
            }
        }
        )
    }
}
