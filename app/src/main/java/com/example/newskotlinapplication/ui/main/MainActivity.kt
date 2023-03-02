package com.example.newskotlinapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.databinding.ActivityMainBinding
import com.example.newskotlinapplication.ui.categories.CategoriesFragment
import com.example.newskotlinapplication.ui.categories.Category
import com.example.newskotlinapplication.ui.categoryDetails.CategoryDetailsFragment

class MainActivity : AppCompatActivity(), CategoriesFragment.OnCategoryClickListener {
    lateinit var viewBinding: ActivityMainBinding
    val categoriesFragment=CategoriesFragment()
    override fun onCategoryClick(item: Category) {
        showCategoriesDetailsFragment(item)
    }

    private fun showCategoriesDetailsFragment(category:Category) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,CategoryDetailsFragment.getInstance(category))
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val toggle = ActionBarDrawerToggle(
            this, viewBinding.root, viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        viewBinding.root.addDrawerListener(toggle)
        toggle.syncState()




        categoriesFragment.onCategoryClickListener=this
        supportFragmentManager.beginTransaction()
                 .replace(R.id.fragment_container,categoriesFragment)
                 .addToBackStack(null)
                 .commit()

    }
}
