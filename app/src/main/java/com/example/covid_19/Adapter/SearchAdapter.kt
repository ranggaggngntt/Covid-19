package com.example.covid_19.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.MyHolder
import com.example.covid_19.R
import com.example.covid_19.model.Attributes
import com.example.covid_19.model.kawalcorona
import com.example.covid_19.model.kawalcoronaItem

class SearchAdapter(private val searchList: List<Attributes>) : RecyclerView.Adapter<MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.global_item, parent, false)
        return MyHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val searchData = searchList[position]
        holder.nameProvinsi.setText(searchData.provinsi)
    }
}
