package com.example.covid_19.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.model.kawalcoronaCountryItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.country_item.*


class CountryAdapter(private var context: Context,
                    private var items:List<kawalcoronaCountryItem>,
                    private var listener: (kawalcoronaCountryItem)-> Unit)
    : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.country_item,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        @SuppressLint("SetTextI18n")
        fun bindItem(item: kawalcoronaCountryItem, listener: (kawalcoronaCountryItem) -> Unit) {
            val nameCountry = containerView.findViewById<TextView>(R.id.nameCountry)
            val positifCountry = containerView.findViewById<TextView>(R.id.positifCountry)
            val sembuhCountry = containerView.findViewById<TextView>(R.id.sembuhCountry)
            val meninggalCountry = containerView.findViewById<TextView>(R.id.meninggalCountry)

            nameCountry.text =item.attributesX.countryRegion
            positifCountry.text = "Positif : " + item.attributesX.confirmed.toString()
            sembuhCountry.text = "Sembuh : " + item.attributesX.recovered.toString()
            meninggalCountry.text = "Meninggal : " + item.attributesX.deaths.toString()
            containerView.setOnClickListener { listener(item) }
        }
    }
}