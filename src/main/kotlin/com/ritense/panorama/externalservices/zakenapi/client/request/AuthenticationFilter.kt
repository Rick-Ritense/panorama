package com.ritense.panorama.externalservices.zakenapi.client.request

interface AuthenticationFilter<T : AuthenticationFilter<T>> {
    fun withBsn(bsn: String): T

    fun withKvk(kvk: String): T

    fun withUid(uid: String): T
}
