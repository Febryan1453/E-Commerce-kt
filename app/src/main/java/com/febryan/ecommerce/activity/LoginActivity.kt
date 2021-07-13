package com.febryan.ecommerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefHelper: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPreference(this)

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val email = binding.edtEmail.text.toString()
        val pass = binding.edtPassword.text.toString()
        if (email.isEmpty()){
            edt_email.error = "Wajib diisi"
            return
            // email.get(error("Wajib diisi"))
        }else if(pass.isEmpty()){
            edt_password.error = "Wajib diisi"
            return
        }

        binding.pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(email, pass).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {

                binding.pb.visibility = View.GONE

                val respon = response.body()

                if (respon != null) {
                    if (respon.status == 0){
                        Toast.makeText(this@LoginActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                    }else{
                        sharedPrefHelper.setStatusLogin(true)
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finish()
                        Toast.makeText(this@LoginActivity, "Sukses: "+respon.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                binding.pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }


}