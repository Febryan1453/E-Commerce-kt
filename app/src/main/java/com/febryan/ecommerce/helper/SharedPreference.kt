package com.febryan.ecommerce.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.febryan.ecommerce.model.Data
import com.google.gson.Gson

class SharedPreference(activity: Activity) {

    val login = "Login"

//    val nama = "Febryan"
//    val telp = "082191170349"
//    val email = "brayenfebryan@gmail.com"

    val user = "user"

    val myPref = "Main_Pref"
    val sharedPreference: SharedPreferences

    init {
        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean){
        sharedPreference.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin():Boolean{
        return sharedPreference.getBoolean(login, false)
    }

//    fun setString(key: String, value: String){
//        sharedPreference.edit().putString(key, value).apply()
//    }
//
//    fun getString(key: String): String {
//        return sharedPreference.getString(key,"")!!
//    }

    fun setUser(value: Data){
        // Mengubah dari object Data ke string
        val data = Gson().toJson(value, Data::class.java)
        sharedPreference.edit().putString(user, data).apply()
    }

    fun getUser():Data?{
        // Mengubah dari string ke object Data
        val data = sharedPreference.getString(user, null) ?: return null
        return Gson().fromJson<Data>(data, Data::class.java)
    }

}