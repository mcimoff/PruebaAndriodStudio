package com.example.myapplication.Api.Model

import android.webkit.ConsoleMessage
import com.example.myapplication.Interface.JsonApi
import com.google.android.gms.common.logging.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.io.Console
import java.util.logging.Level

class Model {

    companion object{


        fun create(baseUrl: String): JsonApi{

            val client = OkHttpClient().newBuilder()
                .build()


            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonApi::class.java)
        }
    }
}