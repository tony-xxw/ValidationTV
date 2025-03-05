package com.aispeech.validationtv.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.aispeech.platform.act.BasicActivity
import com.aispeech.validationtv.adapter.RecipeAdapter
import com.aispeech.validationtv.bean.RecipeBean
import com.aispeech.validationtv.databinding.ActivityRecipeBinding
import com.aispeech.validationtv.ui.recipe.RecipeDetailActivity.Companion.DETAIL_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class RecipeActivity : BasicActivity<ActivityRecipeBinding>() {
    companion object {
        const val TAG = "Recipe"
    }


    private val mAdapter by lazy { RecipeAdapter(mData) }

    private val mData by lazy {
        loadJSONFromAsset(
            this@RecipeActivity,
            "recipe.json"
        ).convertData() ?: mutableListOf()
    }


    override fun initView() {
        binding.rvRecipe.run {
            layoutManager = GridLayoutManager(this@RecipeActivity, 4)
            adapter = mAdapter
        }
        mAdapter.itemClick = {
            Log.d(TAG, "cur : $it")
            startActivity(Intent(this@RecipeActivity, RecipeDetailActivity::class.java).apply {
                putExtra(DETAIL_KEY, mData[it])
            })
        }
    }

    override fun initData() {
    }

    private fun loadJSONFromAsset(context: Context, fileName: String): String {

        try {
            val inputStream = context.assets.open(fileName)
            return inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return ""
    }

    private fun String.convertData(): List<RecipeBean>? {
        if (TextUtils.isEmpty(this)) return null
        var content = JSONObject(this).getString("content") ?: ""

        return Gson().fromJson(content, object : TypeToken<List<RecipeBean>>() {}.type)
    }
}