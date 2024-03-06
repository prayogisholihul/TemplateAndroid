package com.zogik.templateandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 16:18
 **/

@HiltViewModel
class ViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val text: MutableLiveData<String> = MutableLiveData()
    val textVM: LiveData<String> = text

    init {
        text.value = "HELLO WORLD!"
        viewModelScope.launch {
            repo.getApi()
        }
    }
}
