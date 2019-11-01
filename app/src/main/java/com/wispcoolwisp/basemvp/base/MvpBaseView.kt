package com.wispcoolwisp.basemvp.base

import androidx.annotation.StringRes

interface MvpBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(exception: Throwable, func: () -> Unit)

    fun showError(exception: Throwable, func: (vararg: Any) -> Unit, vararg args: Any)

    fun showErrorMessage(@StringRes messageId: Int)

    fun showErrorMessage(message: String?)

    fun hideSoftKeyboard()
}