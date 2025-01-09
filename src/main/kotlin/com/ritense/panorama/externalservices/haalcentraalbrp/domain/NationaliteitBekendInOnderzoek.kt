package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class NationaliteitBekendInOnderzoek(
    val datumIngangOnderzoek: AbstractDatum? = null,
    val type: Boolean? = null,
    val nationaliteit: Boolean? = null,
    val redenOpname: Boolean? = null,
)