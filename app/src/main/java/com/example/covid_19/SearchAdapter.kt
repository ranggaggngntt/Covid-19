package com.example.covid_19

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.model.kawalcoronaItem
import kotlinx.android.synthetic.main.global_item.view.*

class SearchAdapter : RecyclerView.Adapter<MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.global_item, parent, false)
        return MyHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }
}