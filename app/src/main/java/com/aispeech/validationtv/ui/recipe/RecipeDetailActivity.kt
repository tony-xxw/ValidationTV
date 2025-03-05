package com.aispeech.validationtv.ui.recipe

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aispeech.platform.act.BasicActivity
import com.aispeech.validationtv.bean.RecipeBean
import com.aispeech.validationtv.databinding.ActivityRecipeDetailBinding
import com.aispeech.validationtv.ui.recipe.RecipeActivity.Companion.TAG
import com.bumptech.glide.Glide

class RecipeDetailActivity : BasicActivity<ActivityRecipeDetailBinding>() {

    companion object {
        const val DETAIL_KEY = "detail_key"

    }

    private var mData: RecipeBean? = null

    override fun initView() {
        binding.inThumb.let {
            it.tvTtile.text = mData?.dish ?: ""
            Glide.with(it.ivThumb).load(mData?.image ?: "").into(it.ivThumb)


            it.clRecipe.updateLayoutParams<LinearLayout.LayoutParams> {
                height = it.root.resources.displayMetrics.heightPixels / 3
                width = height / 4 * 3
            }
        }
    }

    override fun initData() {
        mData = intent.getParcelableExtra(DETAIL_KEY)
    }
}