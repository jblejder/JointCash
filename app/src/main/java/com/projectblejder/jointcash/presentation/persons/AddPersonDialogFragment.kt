package com.projectblejder.jointcash.presentation.persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.AddPersonDialogFragmentBinding
import com.projectblejder.jointcash.infrastructure.AppDatabase
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.presentation.shared.uiComponents.CustomBottomSheetDialogFragment
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPersonDialogFragment : CustomBottomSheetDialogFragment() {

    lateinit var binding: AddPersonDialogFragmentBinding

    @Inject
    lateinit var db: AppDatabase


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
}
