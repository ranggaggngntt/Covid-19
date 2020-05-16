package com.example.covid_19.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covid_19.R
import kotlinx.android.synthetic.main.activity_knowmore.*
import kotlinx.android.synthetic.main.fragment_home.*

class KnowMoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowmore)

        btnLearnMore.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.alodokter.com/virus-corona"))
            startActivity(intent)
        }
    }
}