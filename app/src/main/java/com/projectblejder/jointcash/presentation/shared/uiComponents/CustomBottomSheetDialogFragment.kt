package com.projectblejder.jointcash.presentation.shared.uiComponents

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.DialogFragment
import android.view.View
import com.projectblejder.jointcash.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.DisposableContainer

open class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    var disposeBag: DisposableContainer = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MyDialog(context!!, theme)
    }

    class MyDialog(context: Context, theme: Int) : BottomSheetDialog(context, theme) {

        override fun show() {
            super.show()
            findViewById<View>(R.id.design_bottom_sheet)?.setBackgroundResource(R.drawable.dialog_background)
        }
    }
}