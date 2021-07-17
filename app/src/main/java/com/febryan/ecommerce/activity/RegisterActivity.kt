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
import com.febryan.ecommerce.helper.SharedPreference
import com.febryan.ecommerce.model.ResponseUser
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var sharedPrefHelper: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPrefHelper = SharedPreference(this)

        btn_register.setOnClickListener {
            register()
        }

        btn_google.setOnClickListener {
            dataDummy()
        }

    }

    private fun dataDummy() {
        edt_nama.setText("Febryan")
        edt_email.setText("@gmail.com")
        edt_phone.setText("082191170001")
        edt_password.setText("idn.id")
    }

    private fun register() {

        val nama = edt_nama.text.toString()
        val email= edt_email.text.toString()
        val telp = edt_phone.text.toString()
        val pass = edt_password.text.toString()

        if (edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom Nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        } else if (edt_email.text.isEmpty()) {
            edt_email.error = "Kolom Email tidak boleh kosong"
            edt_email.requestFocus()
            return
        } else if (edt_phone.text.isEmpty()) {
            edt_phone.error = "Kolom Nomor Telepon tidak boleh kosong"
            edt_phone.requestFocus()
            return
        } else if (edt_password.text.isEmpty()) {
            edt_password.error = "Kolom Password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.register(nama, email, telp, pass).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {

                pb.visibility = View.GONE

                val respon = response.body()

                if (respon != null) {
                    if (respon.status == 0){
                        val toast = Toast.makeText(this@RegisterActivity, respon.message, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER,0,0)
                        toast.show()
                    }else{
                        sharedPrefHelper.setStatusLogin(true)

                        sharedPrefHelper.setUser(respon.data!!)

//                        sharedPrefHelper.setString(sharedPrefHelper.nama, respon.data?.name.toString())
//                        sharedPrefHelper.setString(sharedPrefHelper.telp, respon.data?.telp.toString())
//                        sharedPrefHelper.setString(sharedPrefHelper.email, respon.data?.email.toString())

                        val i = Intent(this@RegisterActivity, MainActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finish()
                        val toast = Toast.makeText(this@RegisterActivity, "Welcome "+respon.data?.name, Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP,0,0)
                        toast.show()

                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}