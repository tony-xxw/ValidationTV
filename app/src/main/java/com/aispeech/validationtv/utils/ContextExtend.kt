package com.aispeech.validationtv.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun <T> Context.startAct(clazz: Class<T>, action: Intent? = null) {
    startActivity(action ?: Intent(this@startAct, clazz))
}