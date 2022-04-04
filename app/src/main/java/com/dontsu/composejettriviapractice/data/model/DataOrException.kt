package com.dontsu.composejettriviapractice.data.model

/**
 * Response에 대한 Wrapper 클래스
 * */
data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)
