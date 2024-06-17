package com.aispeech.validationtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.aispeech.validationtv.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

    }
}
