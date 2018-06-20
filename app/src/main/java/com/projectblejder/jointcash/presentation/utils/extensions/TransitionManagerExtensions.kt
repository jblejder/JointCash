package com.projectblejder.jointcash.presentation.utils.extensions

import android.support.transition.Transition


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
