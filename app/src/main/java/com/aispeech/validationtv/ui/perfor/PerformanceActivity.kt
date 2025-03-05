package com.aispeech.validationtv.ui.perfor

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.aispeech.platform.act.BasicActivity
import com.aispeech.validationtv.databinding.ActivityPerformanceBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.sqrt


class PerformanceActivity : BasicActivity<ActivityPerformanceBinding>() {

    var mCount = 10;
    private var cpuExecutor: ExecutorService? = null
    private var memoryExecutor: ExecutorService? = null


    private fun startCpuStressTest() {
        runOnUiThread { binding.tvCpuTest.text = "CPU Stress Test Running..." }

        // 创建一个线程池来运行多个线程
        cpuExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

        for (i in 0 until Runtime.getRuntime().availableProcessors()) {
            cpuExecutor?.execute(this::performCpuIntensiveTask)
        }
        runOnUiThread { binding.tvCpuTest.text = "CPU Stress Test Completed" }

    }


    private fun startMemoryStressTest() {
        runOnUiThread { binding.tvMemoryTest.text = "Memory Stress Test Running..." }


        // 创建一个线程池来运行多个线程
        memoryExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

        for (i in 0 until Runtime.getRuntime().availableProcessors()) {
            memoryExecutor?.execute(this::performMemoryIntensiveTask)
        }


        // 关闭线程池并等待任务完成
        memoryExecutor?.shutdown()
        try {
            memoryExecutor?.awaitTermination(10, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
        runOnUiThread { binding.tvMemoryTest.text = "Memory Stress Test Completed" }

    }


    private fun performCpuIntensiveTask() {
        val startTime = SystemClock.elapsedRealtime()
        while (SystemClock.elapsedRealtime() - startTime < 10000) { // 10秒测试
            var result = 0.0
            for (i in 0..999999) {
                result += sqrt(i.toDouble())
            }
        }
    }

    private fun performMemoryIntensiveTask() {
        val memoryList: MutableList<ByteArray> = ArrayList()
        while (true) {
            try {
                memoryList.add(ByteArray(1024 * 1024)) // 分配 1MB 内存
                SystemClock.sleep(100) // 每隔 100 毫秒分配内存
            } catch (e: OutOfMemoryError) {
                break
            }
        }
    }

    override fun initView() {
        binding.tvTest.setOnClickListener {
            mCount = 10;
            startCpuStressTest()
            startMemoryStressTest()
        }
    }

    override fun initData() {
    }
}