package com.example.newskotlinapplication.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newskotlinapplication.databinding.ItemCategoryBinding

class CategoriesRecyclerAdapter(val items :List<Category>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        
    }


    override fun getItemCount(): Int=items.size

    class ViewHolder (val itemBinding:ItemCategoryBinding): RecyclerView.ViewHolder(itemBinding.root)
}