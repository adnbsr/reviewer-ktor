package com.reviewer.data

interface GraphConverter<T> {
    fun toGraphObject(): T
}