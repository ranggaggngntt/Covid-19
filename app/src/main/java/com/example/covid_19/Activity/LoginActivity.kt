package com.example.covid_19.Activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.covid_19.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth?=null
    var callbackManager:CallbackManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

         firebaseAuth = FirebaseAuth.getInstance()
         callbackManager = CallbackManager.Factory.create()
        btnFacebookLogin.setReadPermissions("email")
        btnFacebookLogin.setOnClickListener{
            signIn()
        }

    }

    private fun signIn() {
        btnFacebookLogin.registerCallback(callbackManager, object:FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                handleFacebookAccessToken(result!!.accessToken)
            }

            override fun onCancel() {
                TODO("Not yet implemented")
            }

            override fun onError(error: FacebookException?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {

        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        firebaseAuth?.signInWithCredential(credential)
            ?.addOnFailureListener{e->
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }
            ?.addOnSuccessListener {result ->
                val email = result.user?.email
                Toast.makeText(this, "You Logged with email : "+ email,Toast.LENGTH_LONG).show()
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode,resultCode,data)
    }

    private fun printKeyHash() {

        try {
            val info =
                packageManager.getPackageInfo("com.example.covid_19", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray())
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException)
        {

        }
        catch (e:NoSuchAlgorithmException)
        {

        }
    }
}
