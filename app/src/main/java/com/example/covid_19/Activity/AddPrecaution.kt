package com.example.covid_19.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.covid_19.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_precaution.*

class AddPrecaution : AppCompatActivity(), View.OnClickListener {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    override fun onClick(v: View?) {
        when (v) {
            pickImage -> {
                val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iImg, PICK_IMAGE_REQUEST)
            }
            btnAdd -> {
                val name = PrecautionName.text.toString()
                val storageReference = FirebaseStorage
                    .getInstance()
                    .getReference("PencegahanImages/")
                val databaseRef = FirebaseDatabase
                    .getInstance()
                    .getReference("Pencegahan")
                    .push()

                storageReference.putFile(filePath!!)
                    .addOnSuccessListener {
                        storageReference.downloadUrl.addOnSuccessListener {
                            databaseRef.child("id").key
                            databaseRef.child("Profile").setValue(it.toString())
                            databaseRef.child("NamePrecaution").setValue(name)

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
        setContentView(R.layout.activity_add_precaution)
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
