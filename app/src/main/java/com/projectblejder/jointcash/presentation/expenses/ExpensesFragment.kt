package com.projectblejder.jointcash.presentation.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.ExpensesFragmentBinding
import com.projectblejder.jointcash.presentation.shared.uiComponents.BaseFragment
import com.projectblejder.jointcash.presentation.utils.extensions.disposeWith
import javax.inject.Inject

class ExpensesFragment : BaseFragment() {

    lateinit var binding: ExpensesFragmentBinding

    @Inject
    lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ExpensesFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.model = viewModel.state

        viewModel.load().subscribe().disposeWith(disposeBag)

        binding.menuButton.setOnClickListener {
            GroupsDialogFragment().show(childFragmentManager, "groups-dialog")
        }
    }
}
