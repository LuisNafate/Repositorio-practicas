package com.luisnafate.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.Composable



class Counter : ViewModel() {
    val _counter = mutableStateOf(0)
    val counter get() = _counter
    fun add(){
        _counter.value++
    }

}

