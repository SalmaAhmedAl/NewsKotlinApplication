package com.example.newskotlinapplication.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.newskotlinapplication.databinding.FragmentCategoryBinding

class CategoriesFragment:Fragment() {
    lateinit var viewBinding: FragmentCategoryBinding
    lateinit var adapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
    }

    private fun initRecycler() {
        adapter = CategoriesAdapter(Category.getCategoriesList())
        viewBinding.categoriesRecycler.adapter = adapter
        adapter.onItemClickListener = object : CategoriesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: Category) {
                //We need callback in this callback to let MainActivity to move to another fragment >>>so we avoid issues when we open slide menu
                 onCategoryClickListener?.let { onCategoryClickListener->
                     onCategoryClickListener.onCategoryClick(item)
                 }
            }

        }
    }

    //Here we go to making this callback that we need
    var onCategoryClickListener: OnCategoryClickListener ? =null
    interface OnCategoryClickListener{
        fun onCategoryClick(item: Category)
    }
}