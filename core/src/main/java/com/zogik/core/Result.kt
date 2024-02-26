package com.zogik.core

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 14:33
 **/
sealed class Result<out T> {
    data object Idle : Result<Nothing>()
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val code: Int) : Result<Nothing>()
}