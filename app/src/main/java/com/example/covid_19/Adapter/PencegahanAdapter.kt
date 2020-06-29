package com.example.covid_19.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid_19.Fragment.HomeFragment
import com.example.covid_19.R
import com.example.covid_19.db.Pencegahan
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_precaution.view.*
import kotlinx.android.synthetic.main.pencegahan_item.view.*
import kotlinx.android.synthetic.main.update_precaution.view.*

class PencegahanAdapter(val ctx: Context, val users: List<Pencegahan>) : RecyclerView.Adapter<PencegahanAdapter.PencegahanViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PencegahanViewHolder {
        return PencegahanViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.pencegahan_item, viewGroup, false))
    }

    override fun onBindViewHolder(holder: PencegahanViewHolder, position: Int) {
        holder.bindItem(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class PencegahanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItem(user: Pencegahan) {
            val pencegahanText = itemView.findViewById<TextView>(R.id.txtPencegah)
            val ImageView = itemView.findViewById<ImageView>(R.id.pencegahanimageView)
            val edit = itemView.findViewById<Button>(R.id.edit)
            val delete = itemView.findViewById<Button>(R.id.delete)

            pencegahanText.text = user.PrecautionName.toString()
            Glide.with(ctx).load(user.Profile).into(ImageView)

            edit.setOnClickListener { showUpdateDialog(user) }
            delete.setOnClickListener { deleteInfo(user) }
        }

        fun showUpdateDialog(user: Pencegahan) {
            val builder = AlertDialog.Builder(ctx)
            builder.setTitle("Update")

            val inflater = LayoutInflater.from(ctx)
            val view = inflater.inflate(R.layout.update_precaution, null)

            view.updateNamePrecaution.setText(user.PrecautionName)

            builder.setView(view)
            builder.setPositiveButton("Update") { dialog, which ->
                val dbUsers = FirebaseDatabase.getInstance().getReference("Pencegahan")

                val nama = view.updateNamePrecaution.text.toString().trim()

                if (nama.isEmpty()) {
                    view.updateNamePrecaution.error = "Mohon masukan nama"
                    view.updateNamePrecaution.requestFocus()
                    return@setPositiveButton
                }

                val user = Pencegahan(user.id, nama)

                user.id?.let {
                    dbUsers.child(it).setValue(user).addOnCompleteListener {
                        Toast.makeText(ctx, "Berhasil di update.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            builder.setNegativeButton("Tidak") { dialog, which ->

            }

            val alert = builder.create()
            alert.show()
        }

        fun deleteInfo(user: Pencegahan) {
            val progressDialog = ProgressDialog(ctx, R.style.Theme_MaterialComponents_Light_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Menghapus...")
            progressDialog.show()

            val mydatabase = FirebaseDatabase.getInstance().getReference("Pencegahan")
            user.id?.let { mydatabase.child(it).removeValue() }

            Toast.makeText(ctx, "Berhasil dihapus.", Toast.LENGTH_SHORT).show()

            val intent = Intent(ctx, HomeFragment::class.java)
            ctx.startActivity(intent)
        }
    }

}









//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val inflater = LayoutInflater.from(parent.context)
//        return Holder(
//            inflater,
//            parent
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return pencegahanList.size
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bindItem(pencegahanList[position])
//
//    }
//
//
//    class Holder (inflater: LayoutInflater, viewGroup: ViewGroup) :
//        RecyclerView.ViewHolder(inflater.inflate(R.layout.pencegahan_item,viewGroup, false)){
//
//            fun bindItem(item: Pencegahan){
//                val pencegahanText = itemView.findViewById<TextView>(R.id.txtPencegah)
//                val ImageView = itemView.findViewById<ImageView>(R.id.pencegahanimageView)
//                val edit = itemView.findViewById<Button>(R.id.edit)
//                val delete = itemView.findViewById<Button>(R.id.delete)
//
//                pencegahanText.text = item.PrecautionName
//                Glide.with(itemView.context).load(item.Profile).into(ImageView)
//
//                delete.setOnClickListener { deleteInfo(item) }
//                edit.setOnClickListener { showUpdateDialog(item) }
//            }
//
//
//        fun showUpdateDialog(item: Pencegahan) {
//
//            val builder = AlertDialog.Builder(itemView.context)
//
//            builder.setTitle("Update")
//
//            val inflater = LayoutInflater.from(itemView.context)
//            val view = inflater.inflate(R.layout.update_precaution, null)
//
//            view.updateNamePrecaution.setText(item.PrecautionName)
//
//            builder.setView(view)
//            builder.setPositiveButton("Update") { dialog, which ->
//                val dbUsers = FirebaseDatabase.getInstance().getReference("Pencegahan")
//
//                val nama = view.updateNamePrecaution.text.toString().trim()
//
//                if (nama.isEmpty()) {
//                    view.updateNamePrecaution.error = "Mohon masukan nama"
//                    view.updateNamePrecaution.requestFocus()
//                    return@setPositiveButton
//                }
//                val pencegahan = Pencegahan(item.id, nama)
//
//                item.id?.let {
//                    dbUsers.child(it).setValue(pencegahan).addOnCompleteListener {
//                        Toast.makeText(view.context, "Success",Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                builder.setNegativeButton("No") {dialog, which ->
//
//                }
//                val alert = builder.show()
//                alert.show()
//            }
//        }
//        fun deleteInfo(item: Pencegahan) {
//            val progressDialog = ProgressDialog(itemView.context, R.style.Theme_MaterialComponents_Light_Dialog)
//            progressDialog.isIndeterminate = true
//            progressDialog.setMessage("Menghapus...")
//
//            val db = FirebaseDatabase.getInstance().getReference("Pencegahan")
//            item.id?.let { db.child(it).removeValue() }
//
//            Toast.makeText(itemView.context, "Delete", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(itemView.context, PencegahanActivity::class.java)
//            itemView.context.startActivity(intent)
//        }
//    }
