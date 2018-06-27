package com.projectblejder.jointcash.presentation.shared

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("selected")
fun selected(view: View, selected: Boolean) {
    view.isSelected = selected
}
