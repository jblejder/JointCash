package com.projectblejder.jointcash.presentation.persons

import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectblejder.jointcash.databinding.AddPersonFragmentBinding
import com.projectblejder.jointcash.presentation.utils.inTransaction

class AddPersonFragment : Fragment() {

    lateinit var binding: AddPersonFragmentBinding
    lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val handler = Handler()

    private var removingByOutsideClick = false

    override fun onStart() {
        super.onStart()
        handler.post {
            TransitionManager.beginDelayedTransition(binding.coordinatorLayout, createFade())
            binding.touchOutside.visibility = View.VISIBLE
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddPersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.touchOutside.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.coordinatorLayout, createFade())
            binding.touchOutside.visibility = View.INVISIBLE
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            removingByOutsideClick = true
        }
        sheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        sheetBehavior.setBottomSheetCallback(Callback())
        binding.touchOutside.visibility = View.INVISIBLE
    }

    private fun createFade() = Fade().apply {
        excludeChildren(binding.bottomSheet, true)
    }

    private fun removeFragment() {
        fragmentManager?.inTransaction {
            remove(this@AddPersonFragment)
        }
    }

    private fun removeFragmentWithAnimation() {
        val fade = createFade()
        TransitionManager.beginDelayedTransition(binding.coordinatorLayout, fade)
        fade.onTransitionEnd {
            removeFragment()
        }
        binding.touchOutside.visibility = View.INVISIBLE
    }

    inner class Callback : BottomSheetBehavior.BottomSheetCallback() {

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState != BottomSheetBehavior.STATE_HIDDEN) {
                return
            }
            when {
                removingByOutsideClick -> removeFragment()
                else -> removeFragmentWithAnimation()
            }
        }
    }
}

fun Transition.onTransitionEnd(action: (transition: Transition) -> Unit) {
    addListener(TransitionListenerOnTransitionEnd(action))
}

class TransitionListenerOnTransitionEnd(val action: (Transition) -> Unit) : Transition.TransitionListener {
    override fun onTransitionEnd(transition: Transition) {
        action(transition)
    }

    override fun onTransitionResume(transition: Transition) {}

    override fun onTransitionPause(transition: Transition) {}

    override fun onTransitionCancel(transition: Transition) {}

    override fun onTransitionStart(transition: Transition) {}
}
