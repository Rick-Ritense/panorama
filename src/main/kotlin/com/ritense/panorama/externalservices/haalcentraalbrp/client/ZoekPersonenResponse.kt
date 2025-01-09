package com.ritense.panorama.externalservices.haalcentraalbrp.client

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.SIMPLE_NAME, property = "type")
@JsonSubTypes(
    Type(RaadpleegMetBurgerservicenummer::class),
)
interface ZoekPersonenResponse {
    val type: String
}