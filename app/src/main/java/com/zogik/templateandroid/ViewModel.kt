package com.zogik.templateandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 16:18
 **/

@HiltViewModel
class ViewModel @Inject constructor() : ViewModel() {
    private val text: MutableLiveData<String> = MutableLiveData()
    val textVM: LiveData<String> = text

    init {
        text.value = "HELLO WORLD!"
    }
}
