package com.febryan.ecommerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.febryan.ecommerce.MainActivity
import com.febryan.ecommerce.R
import com.febryan.ecommerce.helper.SharedPreference
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var sharedPrefHelper: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        sharedPrefHelper = SharedPreference(this)

        mainButton()

    }

    private fun mainButton(){
        btn_login.setOnClickListener {
//            sharedPrefHelper.setStatusLogin(true)
//            startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_regis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}