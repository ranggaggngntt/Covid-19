package com.example.covid_19.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covid_19.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUser: DatabaseReference
    private var firebaseUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

            btnLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnRegister.setOnClickListener {
                createEmailId()
            }

    }

    private fun createEmailId(){
        val name = NameRegister.text.toString()
        val email = EmailRegister.text.toString()
        val password = PasswordRegister.text.toString()


        if (name == "") {
            Toast.makeText(this@RegisterActivity, "Please Write Name.", Toast.LENGTH_LONG).show()
        }
        else if (email == "") {
            Toast.makeText(this@RegisterActivity, "Please Write Email.", Toast.LENGTH_LONG).show()
        }
        else if (password == "") {
            Toast.makeText(this@RegisterActivity, "Please Write Password.", Toast.LENGTH_LONG).show()
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        firebaseUserId = mAuth.currentUser!!.uid
                        refUser = FirebaseDatabase.getInstance().reference.child("User").child(firebaseUserId)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserId
                        userHashMap["name"] = name
                        userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/covid-19-47de3.appspot.com/o/profile.png?alt=media&token=3cc81c56-9a7b-48e4-9b29-2ddbdbe22f86"

                        refUser.updateChildren(userHashMap)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@RegisterActivity, "Error Message:" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                    else {
                        Toast.makeText(this@RegisterActivity, "Error Message:" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

}
