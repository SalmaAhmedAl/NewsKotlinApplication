package com.example.newskotlinapplication.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapplication.databinding.ItemCategoryBinding

class CategoriesAdapter(val items :List<Category>):RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){

    class ViewHolder(val viewBinding: ItemCategoryBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(category: Category){
           viewBinding.category= category
            viewBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =items.get(position)
        holder.bind(item)
        with(holder.viewBinding) {
            //We use let to set condition and do something with this condition ////apply
            onItemClickListener?.let { clickListener->
                root.setOnClickListener {
                    clickListener.onItemClick(position, items[position])
                }
            }

        }
    }

    override fun getItemCount(): Int= items.size
     var onItemClickListener :OnItemClickListener? =null
    interface OnItemClickListener{
        fun onItemClick(position: Int, item:Category)
    }

}