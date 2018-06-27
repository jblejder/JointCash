package com.projectblejder.jointcash.presentation.expenses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.ExpensesFragmentBinding

class ExpensesFragment : Fragment() {

    lateinit var binding: ExpensesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ExpensesFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.menuButton.setOnClickListener {
            GroupsDialogFragment().show(childFragmentManager, "groups-dialog")
        }
    }
}

