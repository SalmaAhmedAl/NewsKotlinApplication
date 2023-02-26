package com.example.newskotlinapplication.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newskotlinapplication.databinding.FragmentCategoryBinding

class CategoryFragment:Fragment() {
    lateinit var viewBinding : FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= FragmentCategoryBinding.inflate(inflater,container, false)
        return viewBinding.root
    }
}