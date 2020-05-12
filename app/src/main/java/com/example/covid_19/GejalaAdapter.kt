package com.example.covid_19

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

    class GejalaAdapter(
        var context: Context,
        private val gejalaList: ArrayList<GejalaModel>
    ):RecyclerView.Adapter<GejalaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GejalaAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return gejalaList.size
    }

    override fun onBindViewHolder(holder: GejalaAdapter.ViewHolder, position: Int) {
        holder.bindItem(gejalaList.get(position))
    }

    class ViewHolder(inflater: LayoutInflater,viewGroup: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.gejala_item,viewGroup, false)){

        fun bindItem(item: GejalaModel){
            val gejalaText = itemView.findViewById<TextView>(R.id.txtGejala)
            val gejalaDetail = itemView.findViewById<TextView>(R.id.txtGejalaDetail)
            gejalaText.text = item.gejalaText
            gejalaDetail.text = item.gejalaDetail
        }
    }
}