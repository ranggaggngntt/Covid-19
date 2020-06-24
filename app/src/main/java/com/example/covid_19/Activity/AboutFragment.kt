package com.example.covid_19.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.covid_19.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    private lateinit var mAuth : FirebaseAuth

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
    }
}
