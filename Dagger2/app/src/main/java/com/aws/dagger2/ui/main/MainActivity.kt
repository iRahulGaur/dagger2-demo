package com.aws.dagger2.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.aws.dagger2.BaseActivity
import com.aws.dagger2.R
import com.aws.dagger2.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun testFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ProfileFragment())
            .commit()
    }
}