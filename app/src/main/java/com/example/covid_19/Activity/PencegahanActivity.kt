package com.example.covid_19.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.Adapter.PencegahanAdapter
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.example.covid_19.db.Pencegahan
import com.example.covid_19.model.GejalaModel
import com.example.covid_19.model.pencegahanModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pencegahan.*

class PencegahanActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var users: MutableList<Pencegahan>
    lateinit var pencegahanAdapter: PencegahanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencegahan)

        ref = FirebaseDatabase.getInstance().getReference("Pencegahan")
        users = mutableListOf()

        fab.setOnClickListener {
            startActivity(Intent(this, AddPrecaution::class.java))
            finish()
        }

        recyclerViewPencegah.layoutManager = LinearLayoutManager(this)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("Info : ${error.message}")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    users.clear()
                    for (usr in snapshot.children) {
                        val user = usr.getValue(Pencegahan::class.java)
                        users.add(user!!)
                    }

                    pencegahanAdapter = PencegahanAdapter(this@PencegahanActivity,
                        users as ArrayList<Pencegahan>
                    )

                    recyclerViewPencegah.apply {
                        layoutManager = LinearLayoutManager(this@PencegahanActivity)
                        adapter = pencegahanAdapter
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

