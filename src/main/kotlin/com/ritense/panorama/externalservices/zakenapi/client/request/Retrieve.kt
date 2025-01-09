package com.ritense.panorama.externalservices.zakenapi.client.request

fun interface Retrieve<T> {
    suspend fun retrieve(): T
}
