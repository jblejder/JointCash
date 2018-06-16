package com.projectblejder.jointcash.presentation.persons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.PersonsFragmentBinding

class PersonsFragment : Fragment() {

    lateinit var binding: PersonsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PersonsFragmentBinding.inflate(inflater, container, false)

        val adapter = PersonsAdapter()
        adapter.dataSet = listOf("one", "two", "three")
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewPersonButton.setOnClickListener {
            AddPersonDialogFragment().show(childFragmentManager, "dialog")
        }
    }
}
