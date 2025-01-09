package com.ritense.panorama.externalservices.haalcentraalbrp.domain

data class Geboorte(
    val datum: AbstractDatum? = null,
    val land: Waardetabel? = null,
    val plaats: Waardetabel? = null,
    val inOnderzoek: GeboorteInOnderzoek? = null,
)