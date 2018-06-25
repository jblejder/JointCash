package com.projectblejder.jointcash.presentation.persons

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.R
import com.projectblejder.jointcash.databinding.AddPersonDialogFragmentBinding
import com.projectblejder.jointcash.infrastructure.AppDatabase
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPersonDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: AddPersonDialogFragmentBinding

    @Inject
    lateinit var db: AppDatabase
    val disposeBag = CompositeDisposable()

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AddPersonDialogFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.saveButton.setOnClickListener {
            saveNewPerson()
            dismiss()
        }
    }

    private fun saveNewPerson() = Completable
            .create {
                db.persons().create(Person(null, binding.personNameInput.text.toString()))
                it.onComplete()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .disposeWith(disposeBag)

    class MyDialog(context: Context, theme: Int) : BottomSheetDialog(context, theme) {

        override fun show() {
            super.show()
            findViewById<View>(R.id.design_bottom_sheet)?.setBackgroundResource(R.drawable.dialog_background)
        }
    }
}
