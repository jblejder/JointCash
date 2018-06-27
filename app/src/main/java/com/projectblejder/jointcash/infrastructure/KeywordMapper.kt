package com.projectblejder.jointcash.infrastructure

import android.content.res.Resources
import com.projectblejder.jointcash.R
import javax.inject.Inject

class KeywordMapper
@Inject constructor(
        private val resources: Resources
) {

    fun map(value: String) = when (value) {
        "{you}" -> resources.getString(R.string.you)
        "{general}" -> resources.getString(R.string.general)
        else -> value
    }
}
