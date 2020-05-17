package com.example.covid_19.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.model.kawalcoronaItem
import kotlinx.android.extensions.LayoutContainer

class ProvinsiAdapter(private var context: Context,
                      private var items:List<kawalcoronaItem>,
                      private var listener: (kawalcoronaItem)-> Unit)
    : RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.provinsi_item,
            parent, false))

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SetTextI18n")
        fun bindItem(item: kawalcoronaItem, listener: (kawalcoronaItem) -> Unit) {
            val nameProvinsi = containerView.findViewById<TextView>(R.id.nameProvinsi)
            val positifProv = containerView.findViewById<TextView>(R.id.positifProv)
            val sembuhProv = containerView.findViewById<TextView>(R.id.sembuhProv)
            val meninggalProv = containerView.findViewById<TextView>(R.id.meninggalProv)

            nameProvinsi.text = item.attributes.provinsi
            positifProv.text = "Positif : " + item.attributes.kasusPosi.toString()
            sembuhProv.text = "Sembuh : " + item.attributes.kasusSemb.toString()
            meninggalProv.text = "Meninggal : " + item.attributes.kasusMeni.toString()
            containerView.setOnClickListener { listener(item) }

        }
    }
}