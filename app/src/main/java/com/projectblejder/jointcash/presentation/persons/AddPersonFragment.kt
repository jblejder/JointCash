package com.projectblejder.jointcash.presentation.persons

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED
import android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.projectblejder.jointcash.databinding.AddPersonFragmentBinding
import com.projectblejder.jointcash.infrastructure.AppDatabase
import com.projectblejder.jointcash.infrastructure.models.Person
import com.projectblejder.jointcash.presentation.utils.extensions.inTransaction
import com.projectblejder.jointcash.presentation.utils.extensions.onTransitionEnd
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AddPersonFragment : Fragment() {

    lateinit var binding: AddPersonFragmentBinding
    lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val handler = Handler()

    private var removingByOutsideClick = false

    @Inject
    lateinit var db: AppDatabase

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()

        handler.post {
            TransitionManager.beginDelayedTransition(binding.coordinatorLayout, createFade())
            binding.touchOutside.visibility = View.VISIBLE
            sheetBehavior.state = STATE_EXPANDED
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
            sheetBehavior.state = STATE_HIDDEN
            removingByOutsideClick = true
        }
        binding.saveButton.setOnClickListener {
            Completable
                    .create {
                        db.persons().create(Person(null, binding.personNameInput.text.toString()))
                        it.onComplete()
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            sheetBehavior.state = STATE_HIDDEN
            removingByOutsideClick = true
        }
        sheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        sheetBehavior.state = STATE_HIDDEN
        sheetBehavior.setBottomSheetCallback(Callback())
        binding.touchOutside.visibility = View.INVISIBLE
    }

    private fun createFade() = Fade().apply {
        excludeChildren(binding.bottomSheet, true)
    }

    private fun removeFragment() {
        hideKeyboard()
        fragmentManager?.inTransaction {
            remove(this@AddPersonFragment)
        }
    }

    private fun removeFragmentWithAnimation() {
        hideKeyboard()
        val fade = createFade()
        TransitionManager.beginDelayedTransition(binding.coordinatorLayout, fade)
        fade.onTransitionEnd {
            removeFragment()
        }
        binding.touchOutside.visibility = View.INVISIBLE
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.personNameInput.windowToken, 0)
    }

    private fun showKeyboard() {
        binding.personNameInput.requestFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.personNameInput, InputMethodManager.SHOW_IMPLICIT)
    }

    inner class Callback : BottomSheetBehavior.BottomSheetCallback() {

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when {
                newState == STATE_EXPANDED -> showKeyboard()
                newState == STATE_HIDDEN && removingByOutsideClick -> removeFragment()
                newState == STATE_HIDDEN -> removeFragmentWithAnimation()
            }
        }
    }
}
