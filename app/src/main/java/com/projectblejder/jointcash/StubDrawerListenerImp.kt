package com.projectblejder.jointcash

import android.support.v4.widget.DrawerLayout
import android.view.View

class StubDrawerListenerImp : DrawerLayout.DrawerListener {
    override fun onDrawerStateChanged(newState: Int) {}

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    override fun onDrawerClosed(drawerView: View) {}

    override fun onDrawerOpened(drawerView: View) {}
}