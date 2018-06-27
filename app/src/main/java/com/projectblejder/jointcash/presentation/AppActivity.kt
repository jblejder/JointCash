package com.projectblejder.jointcash.presentation

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.MainActivityBinding
import com.projectblejder.jointcash.presentation.AppActivity.Screen.Expenses
import com.projectblejder.jointcash.presentation.AppActivity.Screen.Peoples
import com.projectblejder.jointcash.presentation.expenses.ExpensesFragment
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
            openFragment(ExpensesFragment())
        }

        setUpDrawerClicks()
    }

    private fun setUpDrawerClicks() {
        binding.peoples?.also {
            it.root.setOnClickListener {
                setSelected(Peoples)
                openFragment(PersonsFragment())
            }
        }
        binding.expenses?.also {
            it.root.setOnClickListener {
                setSelected(Expenses)
                openFragment(ExpensesFragment())
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

    fun openFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            replace(binding.content.id, fragment)
        }
    }

    private fun setSelected(screen: Screen) {
        binding.peoples?.apply { root.isSelected = screen == Peoples }
        binding.expenses?.apply { root.isSelected = screen == Expenses }
    }

    enum class Screen {
        Peoples, Expenses
    }
}

interface DrawerKeeper {
    fun toggle()
}
