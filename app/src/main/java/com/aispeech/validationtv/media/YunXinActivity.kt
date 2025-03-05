package com.aispeech.validationtv.media

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aispeech.validationtv.databinding.ActivityYunXinBinding

class YunXinActivity : AppCompatActivity() {
    private val mBinding by lazy { ActivityYunXinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btnSearch.setOnClickListener {
            Log.d("XXW", "111111")
            val intent = Intent();
            intent.action= "com.hhc.huiaichang.AI_ACTION"
            //String uri = "huiaichang://?action=play";
            //String uri = "huiaichang://?action=select_item&m0=1";
            //String uri = "huiaichang://?action=set_volume&m0=20";
            //String uri = "huiaichang://?action=order_song&m0=刘德华&m1=";  //只搜索歌手名
            //String uri = "huiaichang://?action=order_song&m0=&m1=忘情水";  //只搜索歌曲名
            intent.putExtra("uri", "huiaichang://?action=order_song&m0=刘德华&m1=忘情水");
            sendBroadcast(intent);
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","onPause")
    }
}