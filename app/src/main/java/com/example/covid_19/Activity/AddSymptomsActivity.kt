package com.example.covid_19.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_symptoms.*

class AddSymptomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_symptoms)

        btnAdd.setOnClickListener{
            addSymptoms()
        }
    }

    private fun addSymptoms() {
        val name = symptomsName.text.toString().trim()

        if (name.isEmpty())
        {
            symptomsName.error = "Add Name!!"
            return
        } else {

        }

        val ref = FirebaseDatabase.getInstance().getReference("Gejala")

        val gjlId = ref.push().key

        val gjl =Gejala(id = String.toString(), NameSymptoms = String.toString())

        if (gjlId != null){
            ref.child(gjlId).setValue(gjl).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Added", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

