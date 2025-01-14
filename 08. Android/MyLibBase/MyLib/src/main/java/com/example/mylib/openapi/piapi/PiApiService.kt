package com.example.mylib.openapi.piapi

import android.util.Log
import com.example.mylib.openapi.OpenApi
import com.example.mylib.openapi.piapi.data.ControlResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PiApiService {
    @GET("/api/led")
    fun controlLed(
        @Query("target") target: String,
        @Query("value") value: String
    ): Call<ControlResult>

    @GET("/api/servo")
    fun controlServo(
        @Query("target") target: String,
        @Query("value") value: Int
    ): Call<ControlResult>

}

object PiApi : OpenApi(){
    override val TAG = javaClass.simpleName
    override  val BASE_URL = "http://192.168.0.8:8000"

    private val service = retrofit.create(PiApiService::class.java)

    fun controlLed(target: String,value: String,callback: (ControlResult)->Unit){
        service.controlLed(target, value)
            .enqueue(ApiCallback<ControlResult>(callback))
    }

    fun controlServo(target: String, value: Int, callback: (ControlResult)->Unit) {
        service.controlServo(target,value)
            .enqueue(ApiCallback<ControlResult>(callback))
    }

    fun pirSensor(target: String, value: Int, callback: (ControlResult) -> Unit){

    }
}

//object PiApi {
//    val TAG = javaClass.simpleName
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://192.168.0.8:8000")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun getService() = retrofit.create(PiApiService::class.java)
//
//    fun controlLed(target: String, value: String, callback: (ControlResult) -> Unit) {
//        getService()
//            .controlLed(target, value)
//            .enqueue(object: Callback<ControlResult> {
//                override fun onFailure(call: Call<ControlResult>, t: Throwable) {
//                    Log.e(TAG, t.toString())
//                }
//                override fun onResponse(
//                    call: Call<ControlResult>,
//                    response: Response<ControlResult>
//                ) {
//                    if (response.isSuccessful){
//                        val result = response.body()
//                        callback(result!!)
//                    } else {
//                        Log.w(TAG, "${response.code()}, ${response.message()}")
//                        Log.w(TAG, "${response.toString()}")
//                    }
//                }
//
//            })
//    }
//
//    fun controlServo(target: String, value: Int, callback: (ControlResult)->Unit) {
//        getService()
//            .controlServo(target, value)
//            .enqueue(object: Callback<ControlResult> {
//                override fun onFailure(call: Call<ControlResult>, t: Throwable) {
//                    Log.e(TAG, t.toString())
//                }
//                override fun onResponse(
//                    call: Call<ControlResult>,
//                    response: Response<ControlResult>
//                ) {
//                    if(response.isSuccessful) { // code : 200 체크
//                        val result = response.body()    // body()의 리턴 타입 ImageSearchResult?
//                        callback(result!!)
//                    } else {
//                        Log.w(TAG, "${response.code()}, ${response.message()}")
//                        Log.w(TAG, "${response.toString()}")
//                    }
//                }
//            })
//    }
//}