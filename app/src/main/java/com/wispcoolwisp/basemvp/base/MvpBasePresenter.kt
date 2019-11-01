package com.wispcoolwisp.basemvp.base

interface MvpBasePresenter<V : MvpBaseView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()
}