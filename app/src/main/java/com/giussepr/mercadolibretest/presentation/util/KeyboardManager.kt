package com.giussepr.mercadolibretest.presentation.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class KeyboardManager @Inject constructor(private val context: Context) {

    private val inputManager: InputMethodManager = context.getSystemService(InputMethodManager::class.java)

    fun hideKeyboard(view: View) {
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
