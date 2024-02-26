package com.zogik.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 14:43
 **/

// fixme: USE THIS ONLY WITH VIEWBINDING

private typealias FragmentViewBindingInflater<VB> = (
    inflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean,
) -> VB

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: FragmentViewBindingInflater<VB>,
) : Fragment() {

    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
