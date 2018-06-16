package com.projectblejder.jointcash.presentation.persons

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.AddPersonDialogFragmentBinding

class AddPersonDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: AddPersonDialogFragmentBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog(context!!, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AddPersonDialogFragmentBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
    }

    class MyDialog(context: Context, theme: Int) : BottomSheetDialog(context, theme) {

        override fun show() {
            super.show()
            findViewById<View>(R.id.design_bottom_sheet)?.setBackgroundResource(R.drawable.dialog_background)
        }
    }
}
