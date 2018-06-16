package com.projectblejder.jointcash.presentation.persons

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.AddPersonFragmentBinding
import com.projectblejder.jointcash.presentation.utils.inTransaction

class AddPersonFragment : Fragment() {

    lateinit var binding: AddPersonFragmentBinding

    var removed = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddPersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.touchOutside.setOnClickListener {
            removeThis()
        }

        BottomSheetBehavior.from(binding.bottomSheet)?.setBottomSheetCallback(Callback())
    }

    private fun removeThis() {
        if (removed) {
            return
        }
        removed = true

        fragmentManager?.inTransaction {
            remove(this@AddPersonFragment)
        }
    }

    inner class Callback : BottomSheetBehavior.BottomSheetCallback() {

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                removeThis()
            }
        }
    }
}
