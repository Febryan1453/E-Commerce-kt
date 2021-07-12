package com.febryan.ecommerce.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("regis")
    fun register(
        @Field("name")      nama :String,
        @Field("email")     email :String,
        @Field("telp")      telp :String,
        @Field("password")  password :String,
    ):Call<ResponseBody>

}