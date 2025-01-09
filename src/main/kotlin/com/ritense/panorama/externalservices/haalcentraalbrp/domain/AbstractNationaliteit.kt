package com.ritense.panorama.externalservices.haalcentraalbrp.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.SIMPLE_NAME, property = "type")
@JsonSubTypes(
    Type(BehandeldAlsNederlander::class),
    Type(Nationaliteit::class),
    Type(NationaliteitOnbekend::class),
    Type(Staatloos::class),
    Type(VastgesteldNietNederlander::class),
)
interface AbstractNationaliteit {
    val type: String
    val redenOpname: Waardetabel?
    val datumIngangGeldigheid: AbstractDatum?
}