package br.com.mirabilis.daggerviperarchitecture.base.ui

import android.os.Bundle
import br.com.mirabilis.daggerviperarchitecture.base.presenter.PresenterActions
import br.com.mirabilis.daggerviperarchitecture.base.view.PresenterView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by rodrigosimoesrosa on 2019-05-21.
 * Copyright © 2019. All rights reserved.
 */
abstract class MVPBaseActivity<in VIEW : PresenterView, PRESENTER : PresenterActions<VIEW>> : BaseActivity() {

    @Inject
    lateinit var presenter: PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        presenter.attachView(this as VIEW)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
