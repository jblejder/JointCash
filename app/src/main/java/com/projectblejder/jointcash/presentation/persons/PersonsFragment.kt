package com.projectblejder.jointcash.presentation.persons

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.PersonsFragmentBinding
import com.projectblejder.jointcash.presentation.DrawerKeeper
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PersonsFragment : Fragment() {

    lateinit var binding: PersonsFragmentBinding

    @Inject
    lateinit var viewModel: PersonsViewModel

    private val adapter = PersonsAdapter()

    val disposeBag = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PersonsFragmentBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewPersonButton.setOnClickListener {
            AddPersonDialogFragment().show(childFragmentManager, "add-")
        }

        viewModel.database.persons().allRx()
                .onBackpressureDrop()
                .map { it.map { it.displayName } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter.dataSet = it }
                .disposeWith(disposeBag)

        binding.drawerButton.setOnClickListener {
            (activity as DrawerKeeper).toggle()
        }
    }
}
