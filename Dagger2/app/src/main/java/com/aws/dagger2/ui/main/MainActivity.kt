package com.aws.dagger2.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.aws.dagger2.BaseActivity
import com.aws.dagger2.R
import com.aws.dagger2.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun init() {
        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
            android.R.id.home -> {
                return if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOptions: NavOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOptions)
            }
            R.id.nav_posts -> {
                if (isValidProfileDestination()) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
                }
            }
        }

        item.isChecked = true
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun isValidProfileDestination(): Boolean {
        return R.id.postsScreen != Navigation.findNavController(this, R.id.nav_host_fragment)
            .currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), binding.drawerLayout)
    }

}