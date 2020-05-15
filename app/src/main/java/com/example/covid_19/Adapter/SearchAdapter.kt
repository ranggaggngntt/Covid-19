package com.example.covid_19.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.model.Attributes
import kotlinx.android.extensions.LayoutContainer

class SearchAdapter(private val context: Context,
                    private val items:List<Attributes>,
                    private val listener: (Attributes)-> Unit)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.global_item,
            parent, false))

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }
    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Attributes, listener: (Attributes) -> Unit) {
            val nameProvinsi = containerView.findViewById<TextView>(R.id.nameProvinsi)

            nameProvinsi.text = item.provinsi
            containerView.setOnClickListener { listener(item) }
        }
    }

}