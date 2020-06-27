package com.example.covid_19.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.covid_19.Activity.AddSymptomsActivity
import com.example.covid_19.Activity.LoginActivity
import com.example.covid_19.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {

    private lateinit var mAuth : FirebaseAuth
    private val RequestCode = 438
    private var userReference : DatabaseReference? = null
    private var imageUri: Uri? = null
    private var storageRef : StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_about
            ,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initview()

    }

    private fun initview() {

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        name.text = currentUser?.displayName
        email.text = currentUser?.email
        Glide.with(this).load(currentUser?.photoUrl).into(imageprofile)

        logout.setOnClickListener {
            mAuth.signOut()

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        addData.setOnClickListener {
            startActivity(Intent(context, AddSymptomsActivity::class.java))
        }
    }

}
