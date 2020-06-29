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
import androidx.room.Database
import com.example.covid_19.R
import com.example.covid_19.db.Gejala
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add_symptoms.*
import kotlinx.android.synthetic.main.activity_add_symptoms.view.*
import java.io.IOException

class AddSymptomsActivity : AppCompatActivity(), View.OnClickListener {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    override fun onClick(v: View?) {
        when (v) {
            pickImage -> {
                val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iImg, PICK_IMAGE_REQUEST)
            }
            btnAdd -> {
                val name = symptomsName.text.toString()
                val storageReference = FirebaseStorage
                    .getInstance()
                    .getReference("GejalaImage/")
                val databaseRef = FirebaseDatabase
                    .getInstance()
                    .getReference("Gejala")
                    .push()

                storageReference.putFile(filePath!!)
                    .addOnSuccessListener {
                        storageReference.downloadUrl.addOnSuccessListener {
                            databaseRef.child("id").key
                            databaseRef.child("Profile").setValue(it.toString())
                            databaseRef.child("NameSymptoms").setValue(name)

                            Toast.makeText(this, "Add Image Succesfully", Toast.LENGTH_SHORT).show()

                            finish()
                        }
                    }
                    .addOnFailureListener {
                        println("Info File : ${it.message}")
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_symptoms)

        pickImage.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data!!.data != null) {
            filePath = data.data
        }
    }
}
