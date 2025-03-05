package com.aispeech.validationtv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.aispeech.validationtv.R
import com.aispeech.validationtv.bean.RecipeBean
import com.aispeech.validationtv.databinding.ItemRecipeBinding
import com.bumptech.glide.Glide

class RecipeAdapter(var data: List<RecipeBean>) : RecyclerView.Adapter<RecipeViewHolder>() {


    var itemClick: (Int) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            ItemRecipeBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            )
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        (holder.binding as? ItemRecipeBinding)?.let {

            it.clRecipe.updateLayoutParams<GridLayoutManager.LayoutParams> {
                height = it.root.resources.displayMetrics.heightPixels / 3
            }
            it.tvTtile.text = data[position].dish ?: ""
            Glide.with(it.ivThumb).load(data[position].image ?: "").into(it.ivThumb)

            it.root.setOnClickListener {
                itemClick.invoke(position)
            }

            it.root.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    it.root.setBackgroundResource(R.drawable.ic_select_bg)
                    it.root.scaleX = 1.1f
                    it.root.scaleY = 1.1f
                } else {
                    it.root.setBackgroundResource(R.drawable.ic_bg)
                    it.root.scaleX = 1f
                    it.root.scaleY = 1f
                }
            }
        }
    }
}


class RecipeViewHolder(var binding: ViewBinding) : ViewHolder(binding.root) {

}