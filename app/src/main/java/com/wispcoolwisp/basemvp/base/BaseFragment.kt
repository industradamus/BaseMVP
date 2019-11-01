package com.wispcoolwisp.basemvp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.wispcoolwisp.basemvp.R

abstract class BaseFragment<V : MvpBaseView, T : MvpBasePresenter<V>>(private val contentLayoutId: Int = 0) :
    Fragment(), MvpBaseView {

    protected abstract val presenter: T
    protected abstract fun getMvpView(): V

    private val inputMethodManager: InputMethodManager by lazy { context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        presenter.onAttach(getMvpView())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (contentLayoutId != 0) {
            return inflater.inflate(contentLayoutId, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun showLoading() {
        (activity as BaseActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as BaseActivity).hideLoading()
    }

    override fun showError(exception: Throwable, func: () -> Unit) {
        (activity as BaseActivity).showError(exception, func)
    }

    override fun showError(exception: Throwable, func: (vararg: Any) -> Unit, vararg args: Any) {
        (activity as BaseActivity).showError(exception, func, args)
    }

    override fun showErrorMessage(@StringRes messageId: Int) {
        hideSoftKeyboard()
        Snackbar.make(requireView(), getString(messageId), Snackbar.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(message: String?) {
        hideSoftKeyboard()
        Snackbar.make(
            requireView(),
            message ?: getString(R.string.something_wrong_message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun hideSoftKeyboard() {
        val fragmentActivity = activity ?: return
        val view = fragmentActivity.currentFocus ?: return
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}