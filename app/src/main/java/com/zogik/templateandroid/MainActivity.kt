package com.zogik.templateandroid

import android.os.Bundle
import androidx.activity.viewModels
import com.zogik.core.BaseActivity
import com.zogik.templateandroid.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewmodel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.textVM.observe(this) {
            binding.textView.text = it
        }
    }
}
