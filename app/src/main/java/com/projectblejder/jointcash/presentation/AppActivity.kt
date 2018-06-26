package com.projectblejder.jointcash.presentation

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.MainActivityBinding
import com.projectblejder.jointcash.presentation.persons.PersonsFragment
import com.projectblejder.jointcash.presentation.utils.extensions.inTransaction

class AppActivity : AppCompatActivity(), DrawerKeeper {

    lateinit var binding: MainActivityBinding
    lateinit var drawerListener: DrawerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        drawerListener = DrawerListener(binding.content)
        binding.drawerLayout.addDrawerListener(drawerListener)
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                replace(binding.content.id, PersonsFragment())
            }
        }
    }

    override fun toggle() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.START)) {
            binding.drawerLayout.closeDrawer(Gravity.START)
        } else {
            binding.drawerLayout.openDrawer(Gravity.START)
        }
    }
}

interface DrawerKeeper {
    fun toggle()
}
