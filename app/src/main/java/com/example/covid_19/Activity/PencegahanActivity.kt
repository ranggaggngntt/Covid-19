package com.example.covid_19.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Adapter.GejalaAdapter
import com.example.covid_19.Adapter.PencegahanAdapter
import com.example.covid_19.R
import com.example.covid_19.model.GejalaModel
import com.example.covid_19.model.pencegahanModel
import kotlinx.android.synthetic.main.activity_pencegahan.*

class PencegahanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencegahan)

            recyclerViewPencegah.layoutManager = LinearLayoutManager(this)
        val pencegahanList = ArrayList<pencegahanModel>()
        pencegahanList.add(
            pencegahanModel(
                R.drawable.wear_mask,
                "Memakai Masker"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_washing_hands,
                "Cuci Tangan"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_social_distancing,
                "Jaga Jarak"
            )
        )
        pencegahanList.add(
            pencegahanModel(
                R.drawable.ic_stay_home,
                "Tetap Dirumah"
            )
        )
        val gejalaAdapter = PencegahanAdapter(this, pencegahanList)

        recyclerViewPencegah.adapter = gejalaAdapter
        }
    }
