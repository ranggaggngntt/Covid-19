package com.example.covid_19.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid_19.model.GejalaModel
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import kotlinx.android.synthetic.main.gejala_item.view.*

class GejalaAdapter(
        var context: Context,
        private val gejalaList: ArrayList<Gejala>
    ):RecyclerView.Adapter<GejalaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount(): Int {
        return gejalaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(gejalaList[position])
    }

    class ViewHolder(inflater: LayoutInflater,viewGroup: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.gejala_item,viewGroup, false)){

        fun bindItem(item: Gejala){
            val gejalaText = itemView.findViewById<TextView>(R.id.txtGejala)
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            gejalaText.text = item.NameSymptoms
            Glide.with(itemView.context).load(item.Profile).into(itemView.imageView)

        }
    }
}