package com.projectblejder.jointcash

import android.support.v4.widget.DrawerLayout
import android.view.View

class DrawerListener(val view: View) : DrawerLayout.DrawerListener by StubDrawerListenerImp() {

    companion object {
        const val SCALE_FACTOR = 0.2f
        const val TRANSLATION_FACTOR = 0.6f
        const val ELEVATION_FACTOR = 30f
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

        val change = 1 - slideOffset * SCALE_FACTOR

        view.apply {
            scaleX = change
            scaleY = change
            translationX = slideOffset * width * TRANSLATION_FACTOR
            translationZ = slideOffset * ELEVATION_FACTOR
        }
    }
}