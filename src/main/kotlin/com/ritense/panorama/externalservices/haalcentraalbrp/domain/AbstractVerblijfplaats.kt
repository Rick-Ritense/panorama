package com.ritense.panorama.externalservices.haalcentraalbrp.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.SIMPLE_NAME, property = "type")
@JsonSubTypes(
    Type(Adres::class),
    Type(Locatie::class),
    Type(VerblijfplaatsBuitenland::class),
    Type(VerblijfplaatsOnbekend::class),
)
interface AbstractVerblijfplaats {
    val type: String
}