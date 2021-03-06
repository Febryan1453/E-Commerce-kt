package com.febryan.ecommerce.api


import com.febryan.ecommerce.model.ResponseUser
import com.febryan.ecommerce.model.produk.ResponseProduk
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("regis")
    fun register(
        @Field("name")      nama :String,
        @Field("email")     email :String,
        @Field("telp")      telp :String,
        @Field("password")  password :String,
    ):Call<ResponseUser>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email")     email :String,
        @Field("password")  password :String,
    ):Call<ResponseUser>

    @GET("produk")
    fun getProduk(): Call<ResponseProduk>

}