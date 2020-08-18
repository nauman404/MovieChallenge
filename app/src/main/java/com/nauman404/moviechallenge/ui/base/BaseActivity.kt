package com.nauman404.moviechallenge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewBinding] [VB]
 */
abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding>(
        @LayoutRes private  val layoutResId: Int
) : AppCompatActivity() {

    /**
     * It returns [VM] which is assigned to [mViewModel] and initialized in [onCreate]
     */
    abstract fun getViewModel(): VM
    protected lateinit var binding: DB

    @Inject
    protected lateinit var viewModelProvider: ViewModelProvider.Factory

    protected val mViewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)

    }
}