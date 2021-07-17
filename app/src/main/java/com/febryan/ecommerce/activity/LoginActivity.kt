package com.febryan.ecommerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.febryan.ecommerce.MainActivity
import com.febryan.ecommerce.R
import com.febryan.ecommerce.api.ApiConfig
import com.febryan.ecommerce.databinding.ActivityLoginBinding
import com.febryan.ecommerce.helper.SharedPreference
import com.febryan.ecommerce.model.ResponseUser
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

//    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefHelper: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        sharedPrefHelper = SharedPreference(this)

        btn_login.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val email = edt_email.text.toString()
        val pass = edt_password.text.toString()
        if (email.isEmpty()){
            edt_email.error = "Wajib diisi"
            return
            // email.get(error("Wajib diisi"))
        }else if(pass.isEmpty()){
            edt_password.error = "Wajib diisi"
            return
        }

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(email, pass).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {

                pb.visibility = View.GONE

                val respon = response.body()

                if (respon != null) {
                    if (respon.status == 0){
                        val toast = Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER,0,0)
                        toast.show()
                    }else{
                        sharedPrefHelper.setStatusLogin(true)

                        sharedPrefHelper.setUser(respon.data!!)

//                        sharedPrefHelper.setString(sharedPrefHelper.nama, respon.data?.name.toString())
//                        sharedPrefHelper.setString(sharedPrefHelper.telp, respon.data?.telp.toString())
//                        sharedPrefHelper.setString(sharedPrefHelper.email, respon.data?.email.toString())

                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finish()
                        val toast = Toast.makeText(this@LoginActivity, respon.message, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP,0,0)
                        toast.show()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }


}