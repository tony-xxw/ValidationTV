package com.aispeech.validationtv.service

import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.IBinder
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class VocabService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.e("VocabService", "onCreate")
        test()
    }

    private fun test() {
        var json = JSONObject().apply {
            put("name", "kongzhi")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    val gson = Gson()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("VocabService", "onStartCommand")
        intent?.getStringExtra("data")?.let {
            val listType = object : TypeToken<List<JSONObject>>() {}.type
            gson.fromJson<List<JSONObject>>(it, listType).let { arrys ->
                Log.d("VocabService", it.toString())
                arrys.forEach {
                    val name = it.getString("vocab")
                    val age = it.getString("intent")
                    Log.e("VocabService", "Name: $name, Age: $age")
                    startActivity(
                        gson.fromJson<Intent>(age, object : TypeToken<Intent>() {}.type).apply {
                            setFlags(FLAG_ACTIVITY_NEW_TASK)
                        })
                }
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }


}