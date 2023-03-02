package com.example.newskotlinapplication.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapplication.databinding.ItemCategoryBinding

class CategoriesAdapter(val items :List<Category>):RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){

    class ViewHolder(val viewBinding: ItemCategoryBinding):RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =items.get(position)
        with(holder.viewBinding) {
            image.setImageResource(item.imageId)
            title.text= item.title
            container.setCardBackgroundColor( ContextCompat.getColor(holder.itemView.context, item.backgroundColorID))

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