package com.example.covid_19.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_symptoms.*
import kotlinx.android.synthetic.main.activity_add_symptoms.view.*
import java.io.IOException

class AddSymptomsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var symptomsName : EditText
    private lateinit var btnAdd : Button
    private lateinit var pickImage : Button
    private lateinit var imgView : ImageView

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_symptoms)

        symptomsName = findViewById(R.id.symptomsName)
        btnAdd = findViewById(R.id.btnAdd)
        pickImage = findViewById(R.id.pickImage)
        imgView = findViewById(R.id.imgView)


        pickImage.setOnClickListener { uploadImage() }
        btnAdd.setOnClickListener(this)
    }

    private fun uploadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onClick(v: View?) {
        addSymptoms()
    }

    private fun addSymptoms() {
        val name = symptomsName.text.toString().trim()

        if (name.isEmpty()) {
            symptomsName.error = "Add Name!!"
            return
        }
        val ref = FirebaseDatabase.getInstance().getReference("Gejala")

        val gjlId = ref.push().key

        val gjl = Gejala(gjlId,name)

        if (gjlId != null){
            ref.child(gjlId).setValue(gjl).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Added", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

