package com.aispeech.validationtv.ui.flow

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aispeech.platform.act.BasicActivity
import com.aispeech.validationtv.databinding.ActivityFlowBinding
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlowDemoActivity : BasicActivity<ActivityFlowBinding>() {
    override fun initView() {
    }

    override fun initData() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                flowOf(1, 2, 3)
                    .map {
                        it * it
                    }
                    .collect {
                        Log.d("XXW", "collect $it")
                    }
            }

        }
    }


}