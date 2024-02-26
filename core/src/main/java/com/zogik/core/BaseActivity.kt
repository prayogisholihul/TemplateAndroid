package com.zogik.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 14:42
 **/

// fixme: USE THIS ONLY WITH VIEWBINDING
//      example usage:
// class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)

private typealias ActivityViewBindingInflater<VB> = (inflater: LayoutInflater) -> VB

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: ActivityViewBindingInflater<VB>,
) : AppCompatActivity() {

    protected lateinit var binding: VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
    }
}
