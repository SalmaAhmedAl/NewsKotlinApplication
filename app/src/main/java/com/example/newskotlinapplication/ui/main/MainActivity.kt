package com.example.newskotlinapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.databinding.ActivityMainBinding
import com.example.newskotlinapplication.ui.categories.CategoriesFragment
import com.example.newskotlinapplication.ui.categories.Category
import com.example.newskotlinapplication.ui.categoryDetails.CategoryDetailsFragment
import com.example.newskotlinapplication.ui.settings.SettingsFragment

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
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(viewBinding.root)
        val toggle = ActionBarDrawerToggle(
            this, viewBinding.drawerMainActivity, viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
         viewBinding.drawerMainActivity.addDrawerListener(toggle)
        toggle.syncState()

        viewBinding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_categories -> {
                    showCategoryFragment()
                }
                R.id.nav_settings -> {
                    showSettingsFragment()
                }
            }
            //viewBinding.root.closeDrawer()
            return@setNavigationItemSelectedListener true
        }


        categoriesFragment.onCategoryClickListener = this
        showCategoryFragment()
    }

    private fun showCategoryFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,categoriesFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showSettingsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,SettingsFragment())
            .addToBackStack(null)
            .commit()
    }
}
