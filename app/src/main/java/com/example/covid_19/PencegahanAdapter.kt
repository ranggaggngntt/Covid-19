package com.example.covid_19

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PencegahanAdapter(var context: Context,
                        private val pencegahanList: ArrayList<pencegahanModel>
):RecyclerView.Adapter<PencegahanAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PencegahanAdapter.Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return pencegahanList.size
    }

    override fun onBindViewHolder(holder: PencegahanAdapter.Holder, position: Int) {
        holder.bindItem(pencegahanList.get(position))
    }


    class Holder (inflater: LayoutInflater, viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.pencegahan_item,viewGroup, false)){

            fun bindItem(item: pencegahanModel){
                val pencegahanText = itemView.findViewById<TextView>(R.id.txtPencegah)
                pencegahanText.text = item.pencegahanText
            }
        }



}
