package com.example.covid_19.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.R
import com.example.covid_19.model.GejalaModel
import kotlinx.android.synthetic.main.activity_gejala.*

class GejalaActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gejala)
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        val gejalaList = ArrayList<GejalaModel>()
            gejalaList.add(GejalaModel(R.drawable.ic_crough,"Batuk"))
            gejalaList.add(GejalaModel(R.drawable.ic_flu,"Pilek"))
            gejalaList.add(GejalaModel(R.drawable.ic_throat,"Sakit Tenggorokan"))
            gejalaList.add(GejalaModel(R.drawable.ic_fever,"Demam"))
        
        val gejalaAdapter = GejalaAdapter(this, gejalaList)

        recyclerView.adapter = gejalaAdapter
        }
    }

