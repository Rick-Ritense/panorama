package com.ritense.panorama.externalservices.haalcentraalbrp.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.SIMPLE_NAME, property = "type")
@JsonSubTypes(
    Type(Datum::class),
    Type(DatumOnbekend::class),
    Type(JaarDatum::class),
    Type(JaarMaandDatum::class),
)
interface AbstractDatum {
    val type: String
    val langFormaat: String
}